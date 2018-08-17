package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    List<Meal> getAll();

    List<Meal> getAllByUserId(int userId);

    List<MealWithExceed> getFiltered(int userId, int caloriesPerDay,
                                     LocalTime startTime, LocalTime endTime,
                                     LocalDate startDate, LocalDate endDate);
}
