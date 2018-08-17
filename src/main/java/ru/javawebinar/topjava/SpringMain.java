package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.getFiltered(LocalTime.of(4, 30), LocalTime.of(10, 45))
                    .forEach(System.out::println);

            mealRestController.getAll().forEach(System.out::println);

            mealRestController.create(new Meal(LocalDateTime.now(), "test", 1001, 1));
            mealRestController.create(new Meal(LocalDateTime.now(), "test1", 1001, 1));
            mealRestController.getAll().forEach(System.out::println);

            mealRestController.delete(5);
            mealRestController.getAll().forEach(System.out::println);
        }
    }
}
