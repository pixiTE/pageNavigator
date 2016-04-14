package com.navigator.logic;

import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.factory.annotation.Autowired;

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

public abstract class DayIterator {

    @Autowired
    private DayService dayService;

    public Class<? extends WebPage> getAnotherDay(DayType currentDay) {
        DayType day = currentDay;
        while (stayCondition(day)) {
            day = getDay(day);
            if (isVisible(day)) {
                return getPageFor(day);
            }
        }
        return getPageFor(currentDay);
    }

    protected abstract boolean stayCondition(DayType day);

    protected abstract DayType getDay(DayType day);

    private boolean isVisible(DayType dayType) {
        Day day = dayService.findByType(dayType);
        return day.isVisible();
    }

    public Class<? extends WebPage> getPageFor(DayType day) {
        switch (day) {
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
}
