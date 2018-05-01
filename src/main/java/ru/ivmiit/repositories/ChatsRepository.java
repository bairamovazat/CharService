package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.User;

import java.util.List;
import java.util.Optional;

public interface ChatsRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByMembersContains(User user);
    Optional<Chat> findByMembersContainsAndId(User user, Long id);

}
