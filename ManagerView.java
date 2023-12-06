package com.example.project2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.Collection;

@Route
public class ManagerView extends VerticalLayout {

    private final TenantRepository repository;
    private final Editor editor;
    final Grid<Tenant> grid;

    final TextField filter;
    private final Button add;
    private final Button refresh;

    public ManagerView(TenantRepository repository, Editor editor) {
        this.repository = repository;
        this.grid = new Grid<>(Tenant.class);
        add(grid);
        listEmployees(null);
        this.editor = editor;
        this.add = new Button("New tenant", VaadinIcon.PLUS.create());
        this.refresh = new Button("Refresh list", VaadinIcon.REFRESH.create());

        this.filter = new TextField();
        filter.setPlaceholder("Filter by name or ID");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        HorizontalLayout menuBar = new HorizontalLayout(filter, add, refresh);
        add(menuBar, grid, editor);

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.edit(e.getValue());
        });

        add.addClickListener(e -> editor.edit(new Tenant("", "", "", "", "", 0)));
        //List view won't update after you click any edit commands; you'll need to run a filter to see changes.

        refresh.addClickListener(e -> listEmployees(filter.getValue()));
        refresh.addClickListener(e -> editor.setVisible(false));
        //Or click this button to refresh the list.

    }

    private void listEmployees(String filter) {
        //I'll start documentation here because this is where it actually gets meaty.
        //Textfield scans for what user inputs.
        if(filter != null && !filter.isEmpty()){
            if(filter.matches("\\d+")){//Checks if input is a number via regex.
                System.out.println(filter);
                grid.setItems(repository.findById(Long.parseLong(filter))); // If yes, searches by ID
            }else{
                grid.setItems(repository.findByName(filter));// Otherwise, searches by name.
            }
        }else{
            grid.setItems((Collection<Tenant>) repository.findAll());//If the filter box is empty, puts the entire list.
        }
    }

}
