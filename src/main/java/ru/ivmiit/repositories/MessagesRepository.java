package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.Message;

public interface MessagesRepository extends JpaRepository<Message, Long> {
}
