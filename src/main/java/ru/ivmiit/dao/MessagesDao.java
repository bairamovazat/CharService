package ru.ivmiit.dao;

import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;

import java.util.List;

/*
messages v1.0
CREATE TABLE public.message
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    text VARCHAR(511) NOT NULL,
    user BIGINT NOT NULL,
    companion VARCHAR(511) NOT NULL,
    sendDate BIGINT,
    isRead BOOLEAN DEFAULT FALSE  NOT NULL
);
CREATE UNIQUE INDEX message_id_uindex ON public.message (id);
*/
/*
all data with users
SELECT message.id as message_id, text, send_date, is_read, from_user.id as from_user_id, from_user.name as from_user_name, for_user.id as for_user_id, for_user.name as for_user_name FROM "message" LEFT JOIN "user" AS from_user ON from_user = from_user.id LEFT JOIN "user" AS for_user ON message.for_user = for_user.id WHERE from_user = ? or for_user = ?;
*/

/*
messages v1.2
CREATE TABLE public.message
(
    id BIGINT DEFAULT nextval('message_id_seq'::regclass) PRIMARY KEY NOT NULL,
    text VARCHAR(511) NOT NULL,
    user_id BIGINT NOT NULL,
    companion_id BIGINT NOT NULL,
    send_date BIGINT,
    is_read BOOLEAN DEFAULT false NOT NULL,
    send_by_user BOOLEAN NOT NULL,
    chat_id BIGINT
);
CREATE UNIQUE INDEX message_id_uindex ON public.message (id);
CREATE UNIQUE INDEX message_pkey ON public.message (id);
 */

public interface MessagesDao extends CrudDao<Long, Message> {
    List<Message> getMessagesByUser(User user);
    List<Message> getMessagesByUserId(Long id);
}
