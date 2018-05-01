package ru.ivmiit.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.forms.AddChatForm;
import ru.ivmiit.forms.AddMemberForm;
import ru.ivmiit.forms.SendMessageForm;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.ChatsRepository;
import ru.ivmiit.repositories.MessagesRepository;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.services.AuthenticationService;
import ru.ivmiit.services.ChatService;
import ru.ivmiit.transfer.ChatDto;
import ru.ivmiit.transfer.MessageDto;
import ru.ivmiit.transfer.UserDto;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ru.ivmiit.transfer.MessageDto.from;

@Controller
public class ChatController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ChatsRepository chatsRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private ChatService chatService;

    @GetMapping("/chats")
    public String getMainAdminPage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        List<Chat> chats = chatsRepository.findByMembersContains(user);
        chatService.keepFirstMessageInChats(chats);
        model.addAttribute("chats", ChatDto.from(chats));
        model.addAttribute("user", UserDto.from(user));
        return "chat/chats_page";
    }

    @GetMapping("/chat/{chatId}")
    public String getChatPage(@PathVariable("chatId") Long chatId,
                          Authentication authentication,
                          @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        Optional<Chat> chat = chatsRepository.findByMembersContainsAndId(user, chatId);
        if(!chat.isPresent()){
            return "redirect:/chats?error";
        }
        model.addAttribute("chat", ChatDto.from(chat.get()));
        return "chat/chat_page";
    }

    @PostMapping("/chats/add")
    public String addChat(@ModelAttribute("userForm") AddChatForm addChatForm,
                          Authentication authentication,
                          @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        Chat chat = Chat.builder()
                .name(addChatForm.getName())
                .members(Collections.singletonList(user))
                .owner(user)
                .build();
        chatsRepository.save(chat);
        return "redirect:/chats";
    }


    @PostMapping("/chats/add/member")
    public String addMember(@ModelAttribute("userForm") AddMemberForm addMemberForm,
                            Authentication authentication,
                            @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        Optional<Chat> chat = chatsRepository.findByMembersContainsAndId(user, addMemberForm.getChatId());
        if(chat.isPresent()){
            chatService.addMemberToChat(user,chat.get());
            return "redirect:/chats";
        }
        return "redirect:/chats?error";
    }

    @PostMapping("/chats/send")
    public String sendMessage(@ModelAttribute("messageForm") SendMessageForm sendMessageForm,
                              Authentication authentication,
                              @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        Optional<Chat> chat = chatsRepository.findByMembersContainsAndId(user, sendMessageForm.getChatId());

        if (!chat.isPresent() || user == null || sendMessageForm.getMessage() == null) {
            return "redirect:/chats?error=true";

        }
        Message message = Message.builder()
                .user(user)
                .text(sendMessageForm.getMessage())
                .sendDate(new Date())
                .isRead(false)
                .chat(chat.get())
                .build();

        messagesRepository.save(message);
        return "redirect:/chat/" + sendMessageForm.getChatId();
    }


    @GetMapping("/chat/{chatId}/updates/")
    @ResponseBody
    public List<MessageDto> longPoolMessageUpdate(@PathVariable("chatId")Long chatId, @RequestParam("lastMessageId")Long lastMessageId,
                                           Authentication authentication, @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        Optional<Chat> chat = chatsRepository.findByMembersContainsAndId(user, chatId);
        Optional<Message> message = messagesRepository.getMessageById(lastMessageId);
        if(!chat.isPresent()){
            throw new IllegalArgumentException("bad chat id");
        }else if(!message.isPresent()){
            throw new IllegalArgumentException("bad message id");
        }

        chatService.waitNewMessages(chat.get(), message.get());

        return from(messagesRepository.getMessagesByChatAndIdAfter(chat.get(), lastMessageId));
    }

}
