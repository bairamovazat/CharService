package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.Message;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository extends JpaRepository<Message, Long> {
    List<Message> getMessagesByChatAndIdAfter(Chat chat, Long lastMessageId);
    Optional<Message> getMessageById(Long id);

}
