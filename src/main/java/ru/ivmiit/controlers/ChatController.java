package ru.ivmiit.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/chats")
    public String getMainAdminPage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        List<Chat> chats = chatsRepository.findByMembersContains(user);
        model.addAttribute("chats", chats);
        model.addAttribute("userName", user.getLogin());
        return "chat/chats_page";
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
        Chat chat = chatsRepository.findByMembersContainsAndId(user, addMemberForm.getChatId());
        Optional<User> newMember = usersRepository.findOneByLogin(addMemberForm.getUserName());

        chat.getMembers().add(newMember.get());

        chatsRepository.save(chat);
        return "redirect:/chats";
    }

    @PostMapping("/chats/send")
    public String sendMessage(@ModelAttribute("userForm") SendMessageForm sendMessageForm,
                              Authentication authentication,
                              @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        Chat chat = chatsRepository.findByMembersContainsAndId(user, sendMessageForm.getChatId());

        if(chat == null || user == null || sendMessageForm.getMessage() == null){
            return "redirect:/chats?error=true";

        }
        Message message = Message.builder()
                .user(user)
                .text(sendMessageForm.getMessage())
                .sendDate(new Date())
                .isRead(false)
                .chat(chat)
                .build();

        messagesRepository.save(message);
        return "redirect:/chats";
    }

}
