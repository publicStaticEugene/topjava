package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static int MEAL_1_ID = START_SEQ + 2;
    public static int MEAL_2_ID = START_SEQ + 3;
    public static int MEAL_3_ID = START_SEQ + 4;
    public static int MEAL_4_ID = START_SEQ + 5;
    public static int MEAL_5_ID = START_SEQ + 6;
    public static int MEAL_6_ID = START_SEQ + 7;

    public static Meal MEAL_1 = new Meal(MEAL_1_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static Meal MEAL_2 = new Meal(MEAL_2_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static Meal MEAL_3 = new Meal(MEAL_3_ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static Meal MEAL_4 = new Meal(MEAL_4_ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static Meal MEAL_5 = new Meal(MEAL_5_ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static Meal MEAL_6 = new Meal(MEAL_6_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
