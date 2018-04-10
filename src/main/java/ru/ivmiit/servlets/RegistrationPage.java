package ru.ivmiit.servlets;

import ru.ivmiit.models.User;
import ru.ivmiit.dao.CrudDao;
import ru.ivmiit.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration")
public class RegistrationPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
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
                "\tПароль<br>\n" +
                "\t<input type=\"name\" name=\"password\" placeholder=\"Пароль\"><br>\n" +
                "\n" +
                "\t<input type=\"submit\" value=\"Зарегистрируй меня!\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        CrudDao userRepository = ServiceImpl.getInstance().getUserRepository();

        writer.write(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "\t<meta charset=\"utf-8\">\n" +
                        "</head>\n" +
                        "<body>");

        User user = new User(name, password);
        userRepository.save(user);

        writer.write("Success");
        writer.write("</body>\n" +
                "</html>");
    }
}
