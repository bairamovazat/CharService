package ru.ivmiit.models;

public class DBCredentialData {
    static String username = "postgres";
    static String password = "password";
    static String database = "database";
    static String URL = "jdbc:postgresql://localhost:5432/" + database;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DBCredentialData.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DBCredentialData.password = password;
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        DBCredentialData.database = database;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        DBCredentialData.URL = URL;
    }

}
