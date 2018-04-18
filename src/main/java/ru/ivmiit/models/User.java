package ru.ivmiit.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String sessionId;
    private String passwordHash;

    public User(){}
    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
