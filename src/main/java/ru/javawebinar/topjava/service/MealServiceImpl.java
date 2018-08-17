package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(final Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public void delete(final int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(final int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Meal update(final Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAllByUserId(userId);
    }

    @Override
    public List<MealWithExceed> getFiltered(final int userId, final int caloriesPerDay,
                                            final LocalTime startTime, final LocalTime endTime,
                                            final LocalDate startDate, final LocalDate endDate) {
        return repository.getFiltered(userId, caloriesPerDay, startTime, endTime, startDate, endDate);
    }
}