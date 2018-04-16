package ru.ivmiit.servlets;

import ru.ivmiit.models.User;
import ru.ivmiit.service.RegistrationService;
import ru.ivmiit.service.Service;
import ru.ivmiit.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private Service mainService = ServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("error",req.getParameter("error"));
        getServletContext().getRequestDispatcher("/jsp/registration_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (name == null || password == null || name.length() < 5 || password.length() < 5) {
            req.setAttribute("error", "Data invalid");
            resp.sendRedirect("?error=Data invalid");
            return;
        }

        RegistrationService registrationService = mainService.getRegistrationService();
        User user = new User(name, password);

        try {
            registrationService.registerUser(user);
        }catch (IllegalArgumentException e){
            req.setAttribute("error", e.getMessage());
            resp.sendRedirect("?error=" + e.getMessage());
            return;
        }

        resp.sendRedirect("/");
    }
}
