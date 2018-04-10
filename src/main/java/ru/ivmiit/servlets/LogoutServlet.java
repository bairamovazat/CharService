package ru.ivmiit.servlets;

import ru.ivmiit.service.Service;
import ru.ivmiit.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = ServiceImpl.getInstance();
        service.getAuthService().logout(resp);
        resp.sendRedirect("/");
    }
}
