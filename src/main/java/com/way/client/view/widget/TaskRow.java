package com.way.client.view.widget;

import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * Created by way on 21.04.2014.
 */
public class TaskRow extends CustomComponent {

    @UiField("doneField")
    private CheckBox doneField;
    @UiField("nameField")
    private TextField nameField;
    @UiField("deadlineField")
    private DateField deadlineField;
    @UiField("edit")
    private Button edit;
    @UiField("delete")
    private Button delete;

    public TaskRow(String task) {
        setCompositionRoot(Clara.create("/TaskRow.xml", this));
        addStyleName("task-row");

        nameField.setValue(task);
        nameField.setReadOnly(true);
    }

    public void stopEditing() {
        nameField.setReadOnly(true);

        edit.setVisible(true);
        delete.setVisible(true);
    }

    public void startEditing() {
        nameField.setReadOnly(false);
        nameField.focus();

        edit.setVisible(false);
        delete.setVisible(false);
    }

    @UiHandler("doneField")
    public void check(Property.ValueChangeEvent event) {
        if (doneField.getValue()) {
            nameField.addStyleName("completed");
        } else {
            nameField.removeStyleName("completed");
        }
    }

    @UiHandler("nameField")
    public void valueChange(Property.ValueChangeEvent event) {
        stopEditing();
    }

    @UiHandler("nameField")
    public void blue(FieldEvents.BlurEvent event) {
        stopEditing();
    }

    @UiHandler("edit")
    public void editClick(Button.ClickEvent event) {
        startEditing();
    }

    @UiHandler("delete")
    public void deleteClick(Button.ClickEvent event) {
        ((ComponentContainer) getParent()).removeComponent(TaskRow.this);
    }

    @UiHandler("nameField")
    public void dateChange(Property.ValueChangeEvent event) {
        if (deadlineField.getValue() != null) {
            deadlineField.addStyleName("not-empty");
        } else {
            deadlineField.removeStyleName("not-empty");
        }
    }
}
