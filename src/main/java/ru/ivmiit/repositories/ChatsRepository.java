package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.User;

import java.util.List;
import java.util.Optional;

public interface ChatsRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByMembersContains(User user);
    Optional<Chat> findByMembersContainsAndId(User user, Long id);
    Optional<Chat> findById(Long id);

    @Query("select distinct count(chat) from Chat as chat where chat.owner = :user")
    Long countChatByUser(@Param("user") User user);

}
