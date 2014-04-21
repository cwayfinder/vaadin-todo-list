package com.way.client.view.widget;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

/**
 * Created by way on 21.04.2014.
 */
public class ProjectPanel extends CustomComponent {

    private final TextField nameField;
    private final Button edit;
    private final Button delete;

    public ProjectPanel() {
        final VerticalLayout layout = new VerticalLayout();
        layout.addStyleName("project-panel");
        layout.setWidth(700, Unit.PIXELS);


        final HorizontalLayout projectPanel = new HorizontalLayout();
        projectPanel.setSpacing(true);
        projectPanel.addStyleName("project-bar");
        projectPanel.setWidth(100, Unit.PERCENTAGE);
        nameField = new TextField();
        nameField.setValue("Unnamed");
        nameField.setWidth(100, Unit.PERCENTAGE);
        nameField.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
                stopEditing();
            }
        });
        nameField.addShortcutListener(new ShortcutListener("",
                ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                stopEditing();
            }
        });
        projectPanel.addComponent(nameField);
        edit = new Button("Edit", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                startEditing();
            }
        });
        projectPanel.addComponent(edit);
        delete = new Button("Delete", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((ComponentContainer) getParent()).removeComponent(ProjectPanel.this);
//                projectPanel.removeComponent(nameField);
            }
        });
        projectPanel.addComponent(delete);
        projectPanel.setExpandRatio(nameField, 1.0f);
        layout.addComponent(projectPanel);

        HorizontalLayout taskPanel = new HorizontalLayout();
        projectPanel.setSpacing(true);
        taskPanel.addStyleName("add-task-bar");
        taskPanel.setWidth(100, Unit.PERCENTAGE);
        final TextField taskTextField = new TextField();
        taskTextField.setWidth(100, Unit.PERCENTAGE);
        taskPanel.addComponent(taskTextField);
        taskPanel.addComponent(new Button("Add Task", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                TaskRow row = new TaskRow(taskTextField.getValue());
                layout.addComponent(row);
                taskTextField.setValue("");
            }
        }));
        taskPanel.setExpandRatio(taskTextField, 1.0f);
        layout.addComponent(taskPanel);

        setCompositionRoot(layout);
    }

    private void stopEditing() {
        nameField.setReadOnly(true);
        if (nameField.getValue().length() < 2) {
            nameField.setComponentError(new UserError("Must contain 2 symbols at least"));
        }

        edit.setVisible(true);
        delete.setVisible(true);
    }

    public void startEditing() {
        nameField.setReadOnly(false);
        nameField.focus();

        edit.setVisible(false);
        delete.setVisible(false);
    }
}
