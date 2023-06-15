package com.usercrud.user;

import com.usercrud.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/show")
public class UserShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        Long id = Long.valueOf(request.getParameter("id"));
        request.setAttribute("user", userDao.read(id));
        getServletContext()
                .getRequestDispatcher("/users/show.jsp")
                .forward(request, response);

    }

}
