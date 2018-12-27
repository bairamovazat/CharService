package ru.ivmiit.services;

import ru.ivmiit.models.Chat;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;

import java.util.List;

public interface ChatService {
    void addMemberToChat(User user, Chat chat);

    List<Message> waitNewMessages(Chat chat, Message message);

    void keepFirstMessageInChats(List<Chat> chats);

    void addToGeneralChat(User user);
}
