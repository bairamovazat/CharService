package ru.ivmiit.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ivmiit.models.Role;
import ru.ivmiit.repositories.UsersRepository;

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

    @GetMapping("/")
    public String getMainAdminPage(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("users", usersRepository.findAllByRole(Role.USER));
        return "admin";
    }

}
