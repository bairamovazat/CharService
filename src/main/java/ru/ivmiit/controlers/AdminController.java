package ru.ivmiit.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ivmiit.models.Chat;
import ru.ivmiit.models.Role;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.ChatsRepository;
import ru.ivmiit.repositories.MessagesRepository;
import ru.ivmiit.repositories.UsersRepository;
import ru.ivmiit.services.AuthenticationService;
import ru.ivmiit.services.RegistrationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 17.11.2017
 * AdminController
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationService service;

    @Autowired
    private ChatsRepository chatsRepository;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private MessagesRepository messagesRepository;

    @GetMapping("/index")
    public String getMainAdminPage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        if(user.id != 1 && !user.getRole().equals(Role.ADMIN)){
            return "redirect:/";
        }

        model.addAttribute("users", usersRepository.findAll());

        model.addAttribute("chatCount", chatsRepository.count());

        model.addAttribute("messageCount", messagesRepository.count());

        return "admin";
    }

    @GetMapping("/user-info")
    public String getUserInfoPage(@RequestParam("userId") Long userId, Authentication authentication, @ModelAttribute("model") ModelMap model) {
        User user = service.getUserByAuthentication(authentication);
        if(user.id != 1 && !user.getRole().equals(Role.ADMIN)){
            return "redirect:/";
        }
        User userInfo = usersRepository.findOneById(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));

        Long chatCount = chatsRepository.countChatByUser(userInfo);
        Long messageCount = messagesRepository.countMessagesByUser(userInfo);

        model.addAttribute("user", userInfo);
        model.addAttribute("chatCount", chatCount);
        model.addAttribute("messageCount", messageCount);

        return "admin-user-inform";
    }

    @GetMapping("/set-role")
    public String createNewUser(@RequestParam("userId")Long userId, @RequestParam("role") String role,
                                Authentication authentication, @ModelAttribute("model") ModelMap model,
                                RedirectAttributes redirectAttrs) {
        User user = service.getUserByAuthentication(authentication);
        if(user.id != 1 && !user.getRole().equals(Role.ADMIN)){
            return "redirect:/";
        }

        usersRepository.findOneById(userId).ifPresent(newAdmin -> {
            newAdmin.setRole(Role.valueOf(role));
            usersRepository.save(newAdmin);
        });

        redirectAttrs.addAttribute("userId", userId);
        return "redirect:/admin/user-info";
    }


    @PostMapping("/change-password")
    public String changePassword(@RequestParam("userId")Long userId, @RequestParam("password") String password,
                                 Authentication authentication, @ModelAttribute("model") ModelMap model,
                                 RedirectAttributes redirectAttrs) {
        User user = service.getUserByAuthentication(authentication);
        if(user.id != 1 && !user.getRole().equals(Role.ADMIN)){
            return "redirect:/";
        }

        usersRepository.findOneById(userId).ifPresent(changePasswordUser -> {
            registrationService.setNewPassword(changePasswordUser, password);
            usersRepository.save(changePasswordUser);
        });

        redirectAttrs.addAttribute("userId", userId);
        return "redirect:/admin/user-info";
    }

}
