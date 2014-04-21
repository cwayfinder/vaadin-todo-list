package com.way.client.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.way.client.RootLayout;
import com.way.client.view.widget.ProjectPanel;

/**
 * Created by way on 21.04.2014.
 */
public class MainView extends CustomComponent implements View {


    public MainView(Navigator navigator) {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(true);

        final VerticalLayout layout = new VerticalLayout();
        layout.setWidth(728, Unit.PIXELS);

        ProjectPanel panel = new ProjectPanel();
        layout.addComponent(panel);
        panel.startEditing();

        final Button button = new Button("Add TODO List");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                ProjectPanel panel = new ProjectPanel();
                layout.addComponent(panel);
                panel.startEditing();
            }
        });

        CustomLayout projectsPanel = new CustomLayout("main");
        projectsPanel.addComponent(layout, "projects-container");
        projectsPanel.setSizeFull();

        mainLayout.addComponent(projectsPanel);
        mainLayout.addComponent(button);
        mainLayout.setComponentAlignment(projectsPanel, Alignment.MIDDLE_CENTER);
        mainLayout.setComponentAlignment(button, Alignment.BOTTOM_CENTER);
        mainLayout.setExpandRatio(projectsPanel, 1.0f);

        setSizeFull();
        setCompositionRoot(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
