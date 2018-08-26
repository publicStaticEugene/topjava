package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Meal> rowMapper;
    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(final DataSource dataSource,
                                  final JdbcTemplate jdbcTemplate,
                                  final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = BeanPropertyRowMapper.newInstance(Meal.class);
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Meal save(final Meal meal, final int userId) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("userId", userId)
                .addValue("dateTime", meal.getDateTime())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories());
        if (meal.isNew()) {
            Number id = insertMeal.executeAndReturnKey(paramMap);
            meal.setId(id.intValue());
        } else {
            int affectedRows = namedParameterJdbcTemplate.update("" +
                    "UPDATE meals SET date_time=:dateTime, description=:description, calories=:calories " +
                    "WHERE id=:id", paramMap);
            if (affectedRows == 0) {
                return null;
            }
        }
        return meal;
    }

    @Override
    public boolean delete(final int id, final int userId) {
        return jdbcTemplate.update("" +
                "DELETE FROM meals " +
                "WHERE id=" + id) != 0;
    }

    @Override
    public Meal get(final int id, final int userId) {
        return jdbcTemplate.queryForObject("" +
                "SELECT * FROM meals " +
                "WHERE id=?", rowMapper, id);
    }

    @Override
    public List<Meal> getAll(final int userId) {
        return jdbcTemplate.query("" +
                "SELECT * FROM meals " +
                "WHERE user_id=?" +
                "ORDER BY date_time DESC", rowMapper, userId);
    }

    @Override
    public List<Meal> getBetween(final LocalDateTime startDate,
                                 final LocalDateTime endDate,
                                 final int userId) {
        return jdbcTemplate.query("" +
                "SELECT * FROM meals " +
                "WHERE date_time >= ? " +
                "AND date_time <= ?" +
                "ORDER BY date_time DESC", rowMapper,
                startDate, endDate);
    }
}
