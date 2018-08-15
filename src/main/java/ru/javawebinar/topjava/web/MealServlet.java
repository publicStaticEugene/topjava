package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.IMealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.IdCounter;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final IMealDao mealDao = MealDaoImpl.getInstance();
        final String id = request.getParameter("id");
        final String desctiption = request.getParameter("description");
        final LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        final int calories = Integer.parseInt(request.getParameter("calories"));

        if ("".equals(id)) {
            mealDao.insert(new Meal(IdCounter.getId(), dateTime, desctiption, calories));
        } else {
            mealDao.update(new Meal(Integer.parseInt(id), dateTime, desctiption, calories));
        }

        request.setAttribute("meals", MealsUtil.getAllFilteredWithExceeded(mealDao.selectAll(), 2000));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final IMealDao mealDao = MealDaoImpl.getInstance();

        if ("delete".equals(request.getParameter("action"))) {
            final int id = Integer.valueOf(request.getParameter("id"));
            mealDao.delete(id);
        }

        request.setAttribute("meals", MealsUtil.getAllFilteredWithExceeded(mealDao.selectAll(), 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
