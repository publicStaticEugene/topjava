package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;

import java.util.Iterator;
import java.util.List;

public class MealDaoImpl implements IMealDao {
    private static MealDaoImpl ourInstance = new MealDaoImpl();
    private final List<Meal> meals = MealStorage.getMeals();

    private MealDaoImpl() {
    }

    public static MealDaoImpl getInstance() {
        return ourInstance;
    }

    @Override
    public void insert(final Meal meal) {
        meals.add(meal);
    }

    @Override
    public void delete(final Integer id) {
        Iterator<Meal> iter = meals.iterator();
        while (iter.hasNext()) {
            Meal meal = iter.next();
            if (meal.getId() == id) {
                iter.remove();
                return;
            }
        }
    }

    @Override
    public void update(final Meal meal) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == meal.getId()) {
                meals.set(i, meal);
                return;
            }
        }
    }

    @Override
    public List<Meal> selectAll() {
        return MealStorage.getMeals();
    }
}
