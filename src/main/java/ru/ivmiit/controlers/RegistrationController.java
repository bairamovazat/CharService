package ru.ivmiit.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ivmiit.forms.UserRegistrationForm;
import ru.ivmiit.services.RegistrationService;
import ru.ivmiit.validators.UserRegistrationFormValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }

    @PostMapping(value = "/signUp")
    public String signUp(@Valid @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
                         BindingResult errors, RedirectAttributes attributes, Authentication authentication) {
        if(authentication != null){
            return "redirect:/";
        }
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }
        service.register(userRegistrationForm);
        return "success_registration";
    }

    @GetMapping(value = "/signUp")
    public String getSignUpPage(Authentication authentication) {
        if(authentication != null){
            return "redirect:/";
        }
        return "sign_up";
    }
}
