package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(MEAL_3_ID, USER_ID);
        assertMatch(actual, MEAL_3);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void anothersMealGet() {
        service.get(MEAL_3_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_3_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL_6, MEAL_5, MEAL_4, MEAL_2, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void anothersMealDelete() {
        service.delete(MEAL_3_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDate() {
        LocalDate start = LocalDate.of(2015, Month.MAY, 30);
        LocalDate end = LocalDate.of(2015, Month.MAY, 30);
        List<Meal> actual = service.getBetweenDates(start, end, USER_ID);
        assertMatch(actual, MEAL_3, MEAL_2, MEAL_1);
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2, MEAL_1);
    }

    @Test
    public void update() {
        Meal expected = new Meal(MEAL_3);
        expected.setCalories(333);
        expected.setDescription("updated");
        Meal actual = service.update(expected, USER_ID);
        assertMatch(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() {
        Meal meal = new Meal(MEAL_1);
        meal.setId(1);
        service.update(meal, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void anothersMealUpdate() {
        Meal expected = new Meal(MEAL_1);
        expected.setCalories(444);
        expected.setDescription("updated");
        Meal actual = service.update(expected, ADMIN_ID);
        assertMatch(actual, expected);
    }

    @Test
    public void create() {
        Meal expected = new Meal(LocalDateTime.now(), "test", 555);
        Meal actual = service.create(expected, USER_ID);
        expected.setId(MEAL_6_ID + 1);
        assertMatch(actual, expected);
    }
}