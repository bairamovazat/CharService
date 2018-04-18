package ru.ivmiit.servlets;

import ru.ivmiit.service.Service;
import ru.ivmiit.service.ServiceImpl;
import ru.ivmiit.service.SpringService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private Service service = SpringService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.getAuthService().logout(resp);
        resp.sendRedirect("/");
    }
}
