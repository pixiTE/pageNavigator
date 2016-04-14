package com.navigator.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navigator.model.entities.Day;
import com.navigator.model.entities.DayType;
import com.navigator.view.page.week.DefaultPage;
import com.navigator.view.page.week.FridayPage;
import com.navigator.view.page.week.MondayPage;
import com.navigator.view.page.week.SaturdayPage;
import com.navigator.view.page.week.SundayPage;
import com.navigator.view.page.week.ThursdayPage;
import com.navigator.view.page.week.TuesdayPage;
import com.navigator.view.page.week.WednesdayPage;

@Service
public class StepManager {

    @Autowired
    private DayService dayService;

    public Class getNextPage(DayType type) {
        Class<?> currentPage = getPageClass(type);

        if (currentPage.equals(SundayPage.class)) { // is it ok to use sunday as static value?
            return SundayPage.class;
        } else {
            return nextVisiblePage(type);
        }
    }

    public Class getPageClass(DayType type) {
        switch (type) {
        case MONDAY:
            return MondayPage.class;
        case TUESDAY:
            return TuesdayPage.class;
        case WEDNESDAY:
            return WednesdayPage.class;
        case THURSDAY:
            return ThursdayPage.class;
        case FRIDAY:
            return FridayPage.class;
        case SATURDAY:
            return SaturdayPage.class;
        case SUNDAY:
            return SundayPage.class;

        default:
            return DefaultPage.class;
        }
    }

    private Class nextVisiblePage(DayType type) {

        Class currentPage = getPageClass(type);
        int nextDayOrdinal = type.ordinal() + 1;

        for (DayType day : DayType.values()) {
            if (day.ordinal() == nextDayOrdinal) {
                if (isVisible(day)) {
                    return currentPage = getPageClass(day);
                } else {
                    nextDayOrdinal++;
                }
            }
        }
        return currentPage;
    }

    public boolean isVisible(DayType dayType) {
        Day day = dayService.findByType(dayType);
        return day.isVisible();
    }

    public Class getPreviousPage(DayType type) {
        Class<?> currentPage = getPageClass(type);

        if (currentPage.equals(MondayPage.class)) {
            return MondayPage.class;
        } else {
            return previousVisiblePage(type);
        }
    }

    private Class previousVisiblePage(DayType type) { // pavel style :D

        Class currentPage = getPageClass(type);
        int previousTypeOrdinal = type.ordinal() - 1;

        DayType[] values = DayType.values();
        int length = values.length - 1;

        for (int i = length; i >= 0; --i) {
            DayType dayType = values[i];

            if (dayType.ordinal() == previousTypeOrdinal) {
                if (isVisible(dayType)) {
                    currentPage = getPageClass(dayType);
                    return currentPage;
                } else {
                    previousTypeOrdinal--;
                }
            }
        }
        return currentPage;
    }

    public Class getNextVisiblePageAfterDefaultPage() {
        for (DayType type : DayType.values()) {
            if (isVisible(type)) {
                return getPageClass(type);
            }
        }
        return DefaultPage.class;
    }

}
