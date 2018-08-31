package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "" +
                "DELETE FROM Meal m " +
                "WHERE m.id=:id " +
                "AND m.user.id=:userId"),
        @NamedQuery(name = Meal.GET_ALL, query = "" +
                "SELECT m FROM Meal m " +
                "LEFT JOIN FETCH m.user " +
                "WHERE m.user.id=:userId " +
                "ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "" +
                "SELECT m FROM Meal m " +
                "LEFT JOIN FETCH m.user " +
                "WHERE m.user.id=:userId " +
                "AND m.dateTime BETWEEN :startDate AND :endDate " +
                "ORDER BY m.dateTime DESC")
})
@Entity
@Table(name = "meals",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "date_time"},
                name = "meals_unique_user_datetime_idx"))
public class Meal extends AbstractBaseEntity {
    public static final String GET_BETWEEN = "Meal.getBetween";
    public static final String GET_ALL = "Meal.getAll";
    public static final String DELETE = "Meal.delete";

    @Column(name = "date_time", nullable = true)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = true)
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = true)
    @NotNull
    @Range(min = 0, max = 10_000)
    private int calories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(Meal meal) {
        this(meal.id, meal.dateTime, meal.description, meal.calories, meal.user);
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories, null);
    }

    public Meal(LocalDateTime dateTime, String description, int calories, User user) {
        this(null, dateTime, description, calories, user);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories, User user) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}



