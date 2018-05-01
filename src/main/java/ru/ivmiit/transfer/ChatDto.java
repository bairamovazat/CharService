package ru.ivmiit.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDto {
    private Long id;
    private String name;
    private User owner;
    private List<MessageDto> messages;
    private List<UserDto> members;

    public static ChatDto from(Chat chat){
        return ChatDto.builder()
                .id(chat.getId())
                .name(chat.getName())
                .owner(chat.getOwner())
                .messages(MessageDto.from(chat.getMessages()))
                .members(UserDto.from(chat.getMembers()))
                .build();
    }

    public static List<ChatDto> from(List<Chat> chats){
        return chats.stream().map(ChatDto::from).collect(Collectors.toList());

    }
}
