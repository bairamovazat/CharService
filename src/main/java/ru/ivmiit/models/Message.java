package ru.ivmiit.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private Long id;
    private String text;
    private User user;
    private User companion;
    private Date sendDate;
    private boolean isRead;

    @Override
    public String toString() {
        return "from " + user.getName() + " for " + companion.getName() + " \"" + getText() + "\"";
    }

}
