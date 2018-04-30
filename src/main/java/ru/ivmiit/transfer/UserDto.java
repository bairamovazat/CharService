package ru.ivmiit.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.User;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    public Long id;

    private String login;
    private String name;

    private List<Chat> chats;
    private String email;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .chats(user.getChats())
                .email(user.getEmail())
                .build();
    }
}
