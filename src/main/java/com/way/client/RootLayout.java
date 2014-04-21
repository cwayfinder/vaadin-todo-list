package com.way.client;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * Created by way on 21.04.2014.
 */
public class RootLayout extends CssLayout {
    public RootLayout() {
        super();

        addStyleName("root");
        setSizeFull();

        // Unfortunate to use an actual widget here, but since CSS generated
        // elements can't be transitioned yet, we must
        Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        addComponent(bg);
    }
}
