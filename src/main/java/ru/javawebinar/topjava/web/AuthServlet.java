package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuthServlet")
public class AuthServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SecurityUtil.setAuthUserId(Integer.parseInt(request.getParameter("userId")));
        response.sendRedirect("/topjava");
    }
}
