package ru.ivmiit.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 10.11.2017
 * UserRegistrationForm
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {
    private String login;
    private String password;
    private String email;
    private String phone;
    private String name;
}
