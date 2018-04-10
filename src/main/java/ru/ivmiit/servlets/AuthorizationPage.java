package ru.ivmiit.servlets;

import ru.ivmiit.models.User;
import ru.ivmiit.repositories.UserRepositoryImpl;
import ru.ivmiit.service.StorageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AuthorizationPage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\" action=\"/registration\">\n" +
                "\tИмя<br>\n" +
                "\t<input type=\"name\" name=\"name\" placeholder=\"Имя\"><br>\n" +
                "\tПароль\n" +
                "\t<input type=\"name\" name=\"password\" placeholder=\"Пароль\"><br>\n" +
                "\n" +
                "\t<input type=\"submit\" value=\"Отправить\">Авторизоваться\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UserRepositoryImpl userRepository = (UserRepositoryImpl) StorageServiceImpl.getInstance().getUserRepository();
        User user = null;
        try {
            user = userRepository.getUserByNameAndPassword(name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            Cookie cookie = new Cookie("IsNotSessionId","");
            resp.addCookie();
        }
    }
}
