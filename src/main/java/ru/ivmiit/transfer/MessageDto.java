package ru.ivmiit.transfer;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String text;
    private Boolean isRead;
    private UserDto user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date sendDate;


    public static MessageDto from(Message message){
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .isRead(message.getIsRead())
                .sendDate(message.getSendDate())
                .user(UserDto.from(message.getUser()))
                .build();
    }
    public static List<MessageDto> from(List<Message> messages){
        return messages.stream().map(MessageDto::from).collect(Collectors.toList());
    }
}
