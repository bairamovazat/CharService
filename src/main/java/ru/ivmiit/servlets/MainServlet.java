package ru.ivmiit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "\t<meta charset=\"utf-8\">\n" +
                        "</head>\n" +
                        "<body>");
        writer.write("<a href=\"/registration\">Регистрация</a><br>");
        writer.write("<a href=\"/auth\">Авторизация</a><br>");
        writer.write("<a href=\"/products\">Все товары</a><br>");
        writer.write("</body>\n" +
                "</html>");
    }
}
