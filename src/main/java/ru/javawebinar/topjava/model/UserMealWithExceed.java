package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(final UserMeal meal, boolean exceed) {
        this(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }

    public UserMealWithExceed(final LocalDateTime dateTime, final String description, final int calories, final boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }
}
