package ru.ivmiit.servlets;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;
import ru.ivmiit.service.AuthService;
import ru.ivmiit.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
@WebServlet("/auth")
public class AuthorizationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/jsp/auth_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ServiceImpl service = ServiceImpl.getInstance();
        AuthService authService = service.getAuthService();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        UsersDao userRepository = service.getUsersRepository();
        Optional<User> user = userRepository.getUserByNameAndPassword(name,password);
        if(!user.isPresent()){
            try {
                resp.sendRedirect("?error=Bad login or password");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        authService.authorizationByUser(user.get(),resp);
        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
