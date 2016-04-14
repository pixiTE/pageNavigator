package com.navigator.logic;

import org.springframework.stereotype.Service;

import com.navigator.model.entities.DayType;

@Service
public class NextDayIterator extends DayIterator {

    @Override
    protected boolean stayCondition(DayType day) {
        return day != DayType.SUNDAY;
    }

    @Override
    protected DayType getDay(DayType day) {
        return getNextDay(day);
    }

    private DayType getNextDay(DayType currentDay) {
        DayType[] days = DayType.values();
        return days[currentDay.ordinal() + 1];
    }

}
