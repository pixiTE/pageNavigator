package com.navigator.logic;

import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navigator.model.entities.Day;
import com.navigator.model.entities.DayType;
import com.navigator.model.repository.DayRepository;
import com.navigator.view.page.week.DefaultPage;

@Service
public class StepManager {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private NextDayIterator nextDayIterator;

    @Autowired
    private PreviousDayIterator previousDayIterator;

    public Class<? extends WebPage> getStartPage() {
        Day firstVisibleDay = dayRepository.findFirstByVisible(true);
        if (firstVisibleDay == null) {
            return DefaultPage.class;
        }
        return nextDayIterator.getPageFor(firstVisibleDay.getType());
    }

    public Class<? extends WebPage> getNextPage(DayType currentDay) {
        return nextDayIterator.getAnotherDay(currentDay);
    }

    public Class<? extends WebPage> getPreviousPage(DayType currentDay) {
        return previousDayIterator.getAnotherDay(currentDay);
    }

}
