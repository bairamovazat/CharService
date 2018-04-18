package ru.ivmiit.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ivmiit.AppConfig;
import ru.ivmiit.models.User;
import ru.ivmiit.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private Service service = SpringService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Optional<User> user = service.getAuthService().getUserByRequest(req);
        user.ifPresent(user1 -> req.setAttribute("userName", user1.getName()));
        getServletContext().getRequestDispatcher("/jsp/main_page.jsp").forward(req,resp);
    }

}
