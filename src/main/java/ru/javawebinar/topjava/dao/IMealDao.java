package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface IMealDao {

    void insert(Meal meal);

    void delete(Integer id);

    void update(Meal meal);

    List<Meal> selectAll();
}
