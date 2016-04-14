package com.navigator.view.page.week;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.navigator.logic.DayService;
import com.navigator.logic.StepManager;

public class DefaultPage extends WebPage {

    @SpringBean
    private DayService dayService;

    @SpringBean
    private StepManager stepManager;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        createPage();
    }

    public void createPage() {
        createForm();
    }

    private void createForm() {
        Form<?> form = new Form<>("form");
        add(form);

        createStartButton(form);
    }

    private void createStartButton(Form<?> form) {
        Button start = new Button("start") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                setResponsePage(stepManager.getStartPage());
            }
        };
        form.add(start);
    }

}
