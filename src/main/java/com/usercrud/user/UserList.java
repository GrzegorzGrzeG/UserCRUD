package com.usercrud.user;

import com.usercrud.entity.User;
import com.usercrud.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        request.setAttribute("users", userDao.findAll());
        getServletContext()
                .getRequestDispatcher("/users/list.jsp")
                .forward(request, response);

    }

}
