package com.navigator.view.page.week;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.navigator.logic.DayService;
import com.navigator.logic.StepManager;
import com.navigator.model.entities.Day;
import com.navigator.model.entities.DayType;

public abstract class DayPage extends WebPage {

    @SpringBean
    private DayService dayService;

    @SpringBean
    private StepManager stepManager;

    private IModel<Day> day;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        loadDay();
        createPage();
    }

    private void loadDay() {
        day = new Model<Day>(dayService.findByType(getDay()));
    }

    protected abstract DayType getDay();

    public void createPage() {
        Form<?> form = new Form<>("form");
        add(form);

        createNameLabel(form);
        createPreviousButton(form);
        createNextButton(form);
    }

    private void createNameLabel(Form<?> form) {
        Label label = new Label("dayName", new PropertyModel<>(day, "type"));
        String styleAttr = "color: #" + day.getObject()
                                           .getColor();
        label.add(new AttributeModifier("style", styleAttr));
        form.add(label);
    }

    private void createPreviousButton(Form<?> form) {
        Button start = new Button("previous") {

            @Override
            public void onSubmit() {
                setResponsePage(previousPage());

            }

            private Class<? extends Page> previousPage() {
                return stepManager.getPreviousPage(getDay());
            }
        };
        form.add(start);
    }

    private void createNextButton(Form<?> form) {
        Button start = new Button("next") {

            @Override
            public void onSubmit() {
                setResponsePage(stepManager.getNextPage(getDay()));
            }

        };
        form.add(start);
    }

}
