package ru.ivmiit.servlets;

import ru.ivmiit.dao.MessagesDao;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;
import ru.ivmiit.service.AuthService;
import ru.ivmiit.service.Service;
import ru.ivmiit.service.SpringService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {
    private Service service = SpringService.getInstance();
    private MessagesDao messagesRepository = service.getMessagesRepository();
    private UsersDao usersRepository = service.getUsersRepository();
    private AuthService authService = service.getAuthService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = authService.authenticateUserByRequest(req);
        if (!user.isPresent()) {
            resp.sendRedirect("/auth?error=Please login");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HashMap<User, ArrayList<Message>> messagesHashMap = new HashMap<>();
        List<Message> messageList =  messagesRepository.getMessagesByUserId(user.get().getId());

        for (Message message : messageList) {
            User companion = message.getUser().getName().equals(user.get().getName()) ? message.getCompanion() : message.getUser();
            ArrayList<Message> messages = messagesHashMap.get(companion);
            if (messages == null) {
                messages = new ArrayList<>();
                messages.add(message);
                messagesHashMap.put(companion, messages);
            } else {
                messages.add(message);
            }
        }
        req.setAttribute("messages", messagesHashMap);
        req.setAttribute("activeMessages", req.getParameter("activeMessages"));
        getServletContext().getRequestDispatcher("/jsp/messages_page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("user-id");
        String userName = req.getParameter("user-name");
        Optional<User> companion;
        if(userId == null && userName == null){
            resp.sendRedirect("?error=Invalid data");
            return;
        }else if(userId != null){
            companion = usersRepository.find(Long.parseLong(userId));
        }else {
            companion = usersRepository.getUserByName(userName);
        }
        Optional<User> user = authService.authenticateUserByRequest(req);
        String text = req.getParameter("text");



        if (!user.isPresent()) {
            resp.sendRedirect("/auth?error=Please login");
            return;
        } else if (!companion.isPresent()) {
            resp.sendRedirect("?error=User not fount");
            return;
        }

        Message message = Message.builder()
                .text(text)
                .companion(companion.get())
                .user(user.get())
                .sendDate(new Date())
                .isRead(false)
                .build();

        messagesRepository.save(message);
        if(userId != null){
            resp.sendRedirect("?activeMessages=" + userId);
        }else{
            resp.sendRedirect("?activeMessages=" + companion.get().getId());
        }
    }

}
