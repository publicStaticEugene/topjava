package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(final String[] args) {
        final List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> mealsWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(mealsWithExceeded);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(final List<UserMeal> mealList,
                                                                   final LocalTime startTime,
                                                                   final LocalTime endTime,
                                                                   final int caloriesPerDay) {
        // implementation through the cycle
        /*final Map<LocalDate, Integer> caloriesByDays = new HashMap<>();
        LocalDate ld;
        Integer calories;
        for (final UserMeal meal : mealList) {
            ld = meal.getDateTime().toLocalDate();
            calories = meal.getCalories();
            caloriesByDays.put(ld, caloriesByDays.getOrDefault(ld, 0) + calories);
        }

        final List<UserMealWithExceed> result = new ArrayList<>();
        for (final UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(createMealWithExceed(meal, caloriesByDays, caloriesPerDay));
            }
        }
        return result;*/

        // implementation through the stream API
        final Map<LocalDate, Integer> caloriesByDays = new HashMap<>();
        LocalDate ld;
        Integer calories;
        for (final UserMeal meal : mealList) {
            ld = meal.getDateTime().toLocalDate();
            calories = meal.getCalories();
            caloriesByDays.put(ld, caloriesByDays.getOrDefault(ld, 0) + calories);
        }

        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new UserMealWithExceed(meal, caloriesByDays.get(meal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static UserMealWithExceed createMealWithExceed(final UserMeal meal,
                                                           final Map<LocalDate, Integer> caloriesByDays,
                                                           final int caloriesPerDay) {
        if (caloriesByDays.get(meal.getDateTime().toLocalDate()) > caloriesPerDay) {
            return new UserMealWithExceed(meal, true);
        } else {
            return new UserMealWithExceed(meal, false);
        }
    }
}
