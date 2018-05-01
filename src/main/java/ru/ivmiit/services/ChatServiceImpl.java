package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.ChatsRepository;
import ru.ivmiit.repositories.MessagesRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Value("${long-polling.response.wait-time}")
    private Long responseWaitTime;

    @Value("${long-polling.response.update-time}")
    private Long updateTime;

    @Autowired
    private ChatsRepository chatsRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    @Override
    public void addMemberToChat(User user, Chat chat){
        chat.getMembers().add(user);
        chatsRepository.save(chat);
    }

    public List<Message> waitNewMessages(Chat chat, Message message){
        Long startTime = System.currentTimeMillis();
        List<Message> messages;
        do{
            messages = messagesRepository.getMessagesByChatAndIdAfter(chat, message.getId());

            if(messages.size() != 0){
                break;
            }

            try {
                Thread.sleep(updateTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (startTime + responseWaitTime > System.currentTimeMillis());
        return messages;
    }

    //Удаляет все элементы кроме последнего
    @Override
    public void keepFirstMessageInChats(List<Chat> chats){
        chats.forEach(chat ->{
            List<Message> messages = chat.getMessages();
            if(messages != null && messages.size() > 1){
                chat.setMessages(messages.subList(messages.size() - 1, messages.size()));
            }
        });

    }
}
