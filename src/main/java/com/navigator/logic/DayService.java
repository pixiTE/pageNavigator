package com.navigator.logic;

import java.util.List;

import com.navigator.model.entities.Day;
import com.navigator.model.entities.DayType;

public interface DayService {

    void save(Day day);

    void save(List<Day> days);

    List<Day> findAll();

    Day findByType(DayType type);
}
