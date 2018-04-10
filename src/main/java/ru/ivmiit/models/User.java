package ru.ivmiit.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String sessionID;
    private String passwordHash;

    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

}
