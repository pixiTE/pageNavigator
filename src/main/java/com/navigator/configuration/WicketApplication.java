package com.navigator.configuration;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.navigator.view.page.week.DefaultPage;
import com.navigator.view.page.week.FridayPage;
import com.navigator.view.page.week.MondayPage;
import com.navigator.view.page.week.SaturdayPage;
import com.navigator.view.page.week.SundayPage;
import com.navigator.view.page.week.ThursdayPage;
import com.navigator.view.page.week.TuesdayPage;
import com.navigator.view.page.week.WednesdayPage;

@Configuration
public class WicketApplication extends WebApplication {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

        mountPages();
    }

    private void mountPages() {
        mountPackage("/monday", MondayPage.class);
        mountPackage("/tuesday", TuesdayPage.class);
        mountPackage("/wednesday", WednesdayPage.class);
        mountPackage("/thursday", ThursdayPage.class);
        mountPackage("/friday", FridayPage.class);
        mountPackage("/saturday", SaturdayPage.class);
        mountPackage("/sunday", SundayPage.class);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return DefaultPage.class;
    }

}
