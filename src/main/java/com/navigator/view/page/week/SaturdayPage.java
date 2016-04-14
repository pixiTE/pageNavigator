package com.navigator.view.page.week;

import org.apache.wicket.AttributeModifier;
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

public class SaturdayPage extends WebPage {

    @SpringBean
    private DayService dayService;

    @SpringBean
    private StepManager stepManager;

    private IModel<Day> day;

    public SaturdayPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        loadDay();
        createPage();
    }

    private void loadDay() {
        day = new Model<Day>(dayService.findByType(DayType.SATURDAY));
    }

    public void createPage() {
        createForm();
    }

    private void createForm() {
        Form<?> form = new Form<>("form");
        add(form);

        createNameLabel(form);
        createPreviousButton(form);
        createNextButton(form);
    }

    private void createPreviousButton(Form<?> form) {
        Button start = new Button("previous") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                setResponsePage(stepManager.getPreviousPage(DayType.SATURDAY));

            }
        };
        form.add(start);
    }

    private void createNextButton(Form<?> form) {
        Button start = new Button("next") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                setResponsePage(stepManager.getNextPage(DayType.SATURDAY));
            }
        };
        form.add(start);
    }

    private void createNameLabel(Form<?> form) {
        Label label = new Label("dayName", new PropertyModel<>(day, "type"));
        String styleAttr = "color: #" + day.getObject().getColor();
        label.add(new AttributeModifier("style", styleAttr));
        form.add(label);
    }
}
