package ch.martinelli.demo.crud.person;

import ch.martinelli.demo.crud.db.tables.records.PersonRecord;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("People")
@Menu(order = 0, icon = "vaadin:users", title = "People")
public class PeopleView extends VerticalLayout {

    public PeopleView(PersonService personService) {
        setSizeFull();

        Grid<PersonRecord> grid = new Grid<>(PersonRecord.class, false);
        grid.addColumn(PersonRecord::getFirstName).setHeader("First Name").setSortable(true);
        grid.addColumn(PersonRecord::getLastName).setHeader("Last Name").setSortable(true);
        grid.addColumn(PersonRecord::getEmail).setHeader("Email").setSortable(true);
        grid.addColumn(PersonRecord::getPhone).setHeader("Phone").setSortable(true);

        grid.setItems(query -> personService.findAll(query.getOffset(), query.getLimit()).stream());

        grid.setSizeFull();
        add(grid);
    }
}
