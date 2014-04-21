package com.way;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.way.client.view.LoginView;
import com.way.client.view.MainView;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@Title("TODO List")
public class MyVaadinUI extends UI {
    public static final String LOGIN = "login";
    public static final String MAIN = "main";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator = new Navigator(this, this);
        navigator.addView(LOGIN, new LoginView(navigator));
        navigator.addView(MAIN, new MainView(navigator));

        navigator.navigateTo(LOGIN);
    }


}
