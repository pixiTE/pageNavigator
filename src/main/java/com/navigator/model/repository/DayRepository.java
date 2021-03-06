package com.navigator.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navigator.model.entities.Day;
import com.navigator.model.entities.DayType;

public interface DayRepository extends JpaRepository<Day, Long> {

    Day findByType(DayType daytype);

    Day findFirstByVisible(Boolean visible);

}
