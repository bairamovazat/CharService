package ru.ivmiit.servlets;

import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;
import ru.ivmiit.dao.UsersDaoImpl;
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
public class AuthorizationPage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String error = req.getParameter("error");
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n");
        if(error != null){
            writer.write(error);
        }
        writer.write(
                "<form method=\"post\" action=\"/auth\">\n" +
                "\tИмя<br>\n" +
                "\t<input type=\"name\" name=\"name\" placeholder=\"Имя\"><br>\n" +
                "\tПароль<br>\n" +
                "\t<input type=\"name\" name=\"password\" placeholder=\"Пароль\"><br>\n" +
                "\n" +
                "\t<input type=\"submit\" value=\"Авторизоваться\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ServiceImpl service = ServiceImpl.getInstance();
        AuthService authService = service.getAuthService();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        UsersDao userRepository = service.getUserRepository();
        Optional<User> user = userRepository.getUserByNameAndPassword(name,password);
        if(!user.isPresent()){
            try {
                resp.sendRedirect("?error=Не верный логин или пароль");
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
