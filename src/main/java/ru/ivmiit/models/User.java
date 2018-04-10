package ru.ivmiit.models;

import java.time.LocalDate;

public class User implements BaseModel<Long> {
    private Long id;
    private String name;
    private String sessionID;
    private String passwordHash;
    public User(){}

    public User(Long id, String name, String passwordHash, String sessionID) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.sessionID = sessionID;
    }
    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
