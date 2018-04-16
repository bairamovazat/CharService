package ru.ivmiit.servlets;

import ru.ivmiit.models.User;
import ru.ivmiit.service.AuthService;
import ru.ivmiit.service.Service;
import ru.ivmiit.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceImpl.getInstance();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        AuthService authService = service.getAuthService();
        Optional<User> user = authService.getUserByRequest(req);
        user.ifPresent(user1 -> req.setAttribute("userName", user1.getName()));
        getServletContext().getRequestDispatcher("/jsp/main_page.jsp").forward(req,resp);
    }
}
