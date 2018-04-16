package ru.ivmiit.servlets;

import ru.ivmiit.dao.MessagesDao;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;
import ru.ivmiit.service.AuthService;
import ru.ivmiit.service.Service;
import ru.ivmiit.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {
    private Service service = ServiceImpl.getInstance();
    private MessagesDao messagesRepository = service.getMessagesRepository();
    private UsersDao usersRepository = service.getUsersRepository();
    private AuthService authService = service.getAuthService();

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = authService.getUserByRequest(req);
        if (!user.isPresent()) {
            resp.sendRedirect("/auth?error=Please login");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HashMap<User, ArrayList<Message>> messagesHashMap = new HashMap<>();
        List<Message> messageList =  messagesRepository.getMessagesByUserId(user.get().getId());

        for (Message message : messageList) {
            //если сообщение от нас, то компаньон for иначе from
            User companion = message.getFromUser().getName().equals(user.get().getName()) ? message.getForUser() : message.getFromUser();
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
        Optional<User> forUser;
        if(userId == null && userName == null){
            resp.sendRedirect("?error=Invalid data");
            return;
        }else if(userId != null){
            forUser = usersRepository.find(Long.parseLong(userId));
        }else {
            forUser = usersRepository.getUserByName(userName);
        }
        Optional<User> fromUser = authService.getUserByRequest(req);
        String text = req.getParameter("text");



        if (!fromUser.isPresent()) {
            resp.sendRedirect("/auth?error=Please login");
            return;
        } else if (!forUser.isPresent()) {
            resp.sendRedirect("?error=User not fount");
            return;
        }

        Message message = Message.builder()
                .text(text)
                .forUser(forUser.get())
                .fromUser(fromUser.get())
                .sendDate(new Date())
                .isRead(false)
                .build();

        messagesRepository.save(message);
        if(userId != null){
            resp.sendRedirect("?activeMessages=" + userId);
        }else{
            resp.sendRedirect("?activeMessages=" + forUser.get().getId());
        }
    }

}
