package ru.ivmiit.servlets;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;
import ru.ivmiit.service.AuthService;
import ru.ivmiit.service.Service;
import ru.ivmiit.service.ServiceImpl;
import ru.ivmiit.service.SpringService;

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
public class AuthorizationServlet extends HttpServlet {
    private Service service = SpringService.getInstance();
    private AuthService authService = service.getAuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/jsp/auth_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        Optional<User> user = authService.authorizationUserByLoginAndPassword(name, password, resp);
        if (!user.isPresent()) {
            resp.sendRedirect("?error=Bad login or password");
            return;
        }
        resp.sendRedirect("/");

    }
}
