package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("People")
@Menu(order = 0, icon = "vaadin:users", title = "People")
public class PeopleView extends VerticalLayout {

    private final PersonService personService;
    private final Grid<PersonRecord> grid;
    private final PersonForm form;

    public PeopleView(PersonService personService) {
        this.personService = personService;
        setSizeFull();

        grid = createGrid();
        form = createForm();

        Button addButton = new Button("Add Person");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickListener(_ -> {
            grid.asSingleSelect().clear();
            editPerson(personService.createNew());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addButton);

        SplitLayout splitLayout = new SplitLayout(grid, form);
        splitLayout.setSizeFull();
        splitLayout.setSplitterPosition(70);

        form.setVisible(false);

        add(toolbar, splitLayout);
    }

    private Grid<PersonRecord> createGrid() {
        Grid<PersonRecord> grid = new Grid<>(PersonRecord.class, false);
        grid.addColumn(PersonRecord::getFirstName).setHeader("First Name").setSortable(true);
        grid.addColumn(PersonRecord::getLastName).setHeader("Last Name").setSortable(true);
        grid.addColumn(PersonRecord::getEmail).setHeader("Email").setSortable(true);
        grid.addColumn(PersonRecord::getPhone).setHeader("Phone").setSortable(true);

        grid.setItems(query -> personService.findAll(query.getOffset(), query.getLimit()).stream());

        grid.asSingleSelect().addValueChangeListener(event -> {
            PersonRecord selected = event.getValue();
            if (selected != null) {
                personService.findById(selected.getId()).ifPresentOrElse(
                        this::editPerson,
                        () -> {
                            refreshGrid();
                            showError("Person not found");
                        }
                );
            } else {
                closeForm();
            }
        });

        grid.setSizeFull();
        return grid;
    }

    private PersonForm createForm() {
        PersonForm form = new PersonForm();
        form.setSaveHandler(person -> {
            try {
                personService.save(person);
                refreshGrid();
                closeForm();
                showSuccess("Person saved");
            } catch (Exception e) {
                showError("Failed to save person");
            }
        });
        form.setCancelHandler(this::closeForm);
        return form;
    }

    private void editPerson(PersonRecord person) {
        form.setPerson(person);
        form.setVisible(true);
    }

    private void closeForm() {
        form.setVisible(false);
        grid.asSingleSelect().clear();
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

    private void showSuccess(String message) {
        Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_START);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private void showError(String message) {
        Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_START);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
