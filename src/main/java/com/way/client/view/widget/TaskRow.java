package com.way.client.view.widget;

import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

/**
 * Created by way on 21.04.2014.
 */
public class TaskRow extends CustomComponent {

    private final TextField nameField;
    private final Button edit;
    private final Button delete;

    public TaskRow(String task) {
        final HorizontalLayout layout = new HorizontalLayout();

        final CheckBox doneField = new CheckBox();
        doneField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (doneField.getValue()) {
                    nameField.addStyleName("completed");
                } else {
                    nameField.removeStyleName("completed");
                }
            }
        });
        nameField = new TextField();
        nameField.setValue(task);
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
        edit = new Button("Edit", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                startEditing();
            }
        });
        delete = new Button("Delete", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ((ComponentContainer) getParent()).removeComponent(TaskRow.this);
            }
        });
        final DateField deadlineField = new DateField();
        deadlineField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (deadlineField.getValue() != null) {
                    deadlineField.addStyleName("not-empty");
                } else {
                    deadlineField.removeStyleName("not-empty");
                }
            }
        });

        layout.addComponents(doneField, nameField, edit, delete, deadlineField);
        layout.setSpacing(true);
        layout.addStyleName("task-row");
        layout.setWidth(100, Unit.PERCENTAGE);
        nameField.setWidth(100, Unit.PERCENTAGE);
        layout.setExpandRatio(nameField, 1.0f);


        nameField.setReadOnly(true);

        setCompositionRoot(layout);
    }

    public void stopEditing() {
        nameField.setReadOnly(true);
//        if (nameField.getValue().length() < 2) {
//            nameField.setComponentError(new UserError("Must contain 2 symbols at least"));
//        }

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
