package com.navigator.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.week.model.generic.JpaEntity;

@Entity
public class Day extends JpaEntity {

    @Enumerated(EnumType.STRING)
    private DayType type;

    private Boolean visible;

    private String color;

    public Day() {
        // needed for JPA and Wicket
    }

    public Day(DayType type) {
        this.type = type;
    }

    public DayType getType() {
        return type;
    }

    public void setType(DayType type) {
        this.type = type;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Day [type=" + type + ", visible=" + visible + ", color=" + color + "]";
    }

}
