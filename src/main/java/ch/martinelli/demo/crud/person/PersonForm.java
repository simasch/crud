package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;

import java.util.function.Consumer;

public class PersonForm extends FormLayout {

    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final TextField phone = new TextField("Phone");
    private final DatePicker birthDate = new DatePicker("Birth Date");

    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");

    private final Binder<PersonRecord> binder = new Binder<>(PersonRecord.class);

    private PersonRecord person;
    private transient Consumer<PersonRecord> saveHandler;
    private transient Runnable cancelHandler;

    public PersonForm() {
        configureFields();
        configureBinder();

        add(firstName, lastName, email, phone, birthDate, createButtonLayout());
    }

    private void configureFields() {
        firstName.setMaxLength(100);
        lastName.setMaxLength(100);
        email.setMaxLength(255);
        email.setClearButtonVisible(true);
        phone.setMaxLength(20);
    }

    private void configureBinder() {
        binder.forField(firstName)
                .asRequired("First name is required")
                .bind(PersonRecord::getFirstName, PersonRecord::setFirstName);

        binder.forField(lastName)
                .asRequired("Last name is required")
                .bind(PersonRecord::getLastName, PersonRecord::setLastName);

        binder.forField(email)
                .withValidator(value -> value == null || value.isEmpty() || !new EmailValidator("").apply(value, null).isError(),
                        "Enter a valid email address")
                .bind(PersonRecord::getEmail, PersonRecord::setEmail);

        binder.forField(phone)
                .bind(PersonRecord::getPhone, PersonRecord::setPhone);

        binder.forField(birthDate)
                .bind(PersonRecord::getBirthDate, PersonRecord::setBirthDate);
    }

    private HorizontalLayout createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(_ -> {
            if (person != null && saveHandler != null) {
                try {
                    binder.writeBean(person);
                    saveHandler.accept(person);
                } catch (ValidationException _) {
                    // Validation errors are shown in the form
                }
            }
        });

        cancel.addClickListener(_ -> {
            if (cancelHandler != null) {
                cancelHandler.run();
            }
        });

        return new HorizontalLayout(save, cancel);
    }

    public void setPerson(PersonRecord person) {
        this.person = person;
        binder.readBean(person);
    }

    public void setSaveHandler(Consumer<PersonRecord> saveHandler) {
        this.saveHandler = saveHandler;
    }

    public void setCancelHandler(Runnable cancelHandler) {
        this.cancelHandler = cancelHandler;
    }
}
