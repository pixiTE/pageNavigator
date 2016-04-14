package com.navigator.logic;

import org.springframework.stereotype.Service;

import com.navigator.model.entities.DayType;

@Service
public class PreviousDayIterator extends DayIterator {

    @Override
    protected boolean stayCondition(DayType day) {
        return day != DayType.MONDAY;
    }

    @Override
    protected DayType getDay(DayType day) {
        return getPreviousDay(day);
    }

    private DayType getPreviousDay(DayType currentDay) {
        DayType[] days = DayType.values();
        return days[currentDay.ordinal() - 1];
    }

}
