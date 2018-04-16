package ru.ivmiit.dao;

import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;

import java.util.List;

/*
CREATE TABLE public.message
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    text VARCHAR(511) NOT NULL,
    fromUser BIGINT NOT NULL,
    forUser VARCHAR(511) NOT NULL,
    sendDate BIGINT,
    isRead BOOLEAN DEFAULT FALSE  NOT NULL
);
CREATE UNIQUE INDEX message_id_uindex ON public.message (id);
 */
public interface MessagesDao extends CrudDao<Long, Message> {
    List<Message> getMessagesByUser(User user);
    List<Message> getMessagesByUserId(Long id);
}
