package com.usercrud.user;

import com.usercrud.entity.User;
import com.usercrud.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        userDao.delete(Long.parseLong(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/user/list");
    }


}
