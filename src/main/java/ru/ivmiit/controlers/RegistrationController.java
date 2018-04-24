package ru.ivmiit.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ivmiit.forms.UserRegistrationForm;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.security.states.State;
import ru.ivmiit.services.RegistrationService;
import ru.ivmiit.validators.UserRegistrationFormValidator;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * 10.11.2017
 * RegistrationController
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;

    @Autowired
    private UsersRepository usersRepository;

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }

    @GetMapping(value = "/confirm/{file-name}")
    public String confirmEmail(@PathVariable("file-name") String fileName, RedirectAttributes attributes) {
        Optional<User> optionalUser = usersRepository.findByUuid(UUID.fromString(fileName));
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setState(State.CONFIRMED);
            usersRepository.save(user);
            attributes.addFlashAttribute("error", "Пользователь подтверждён");
        }
        return "redirect:/login";
    }

    @PostMapping(value = "/signUp")
    public String signUp(@Valid @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
                         BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }
        service.register(userRegistrationForm);
        return "success_registration";
    }

    @GetMapping(value = "/signUp")
    public String getSignUpPage() {
        return "sign_up";
    }
}
