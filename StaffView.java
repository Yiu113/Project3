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
public class StaffView extends VerticalLayout  {
    private final RequestRepository repository;
    private final StaffEditor editorStaff;
    final Grid<MaintReq> grid;
//A staff member must be able to browse all maintenance requests, with a variety of filters: by
//apartment number, by area (like kitchen), by date range, and by status.
    final TextField filterApartment;
    final TextField filterArea;
    final TextField filterDate;
    final TextField filterStatus;
    private final Button refresh;

    public StaffView(RequestRepository repository, StaffEditor editorStaff) {
        this.repository = repository;
        this.grid = new Grid<>(MaintReq.class);

        com.vaadin.flow.component.HasComponents add;
        add(grid);
        listRequests(null);
        this.editorStaff = editorStaff;
        this.refresh = new Button("Refresh list", VaadinIcon.REFRESH.create());

        this.filterApartment = new TextField("Only one field may have text.");
        filterApartment.setPlaceholder("Filter by apartment Number");
        filterApartment.setValueChangeMode(ValueChangeMode.LAZY);
            filterApartment.addValueChangeListener(e -> listRequests(e.getValue()));


        this.filterArea = new TextField(" ");
        filterArea.setPlaceholder("Filter by area");
        filterArea.setValueChangeMode(ValueChangeMode.LAZY);
            filterArea.addValueChangeListener(e -> listRequests(e.getValue()));


        this.filterDate = new TextField(" ");
        filterDate.setPlaceholder("Filter by date");
        filterDate.setValueChangeMode(ValueChangeMode.LAZY);
            filterDate.addValueChangeListener(e -> listRequests(e.getValue()));

        this.filterStatus = new TextField(" ");
        filterStatus.setPlaceholder("Filter by status");
        filterStatus.setValueChangeMode(ValueChangeMode.LAZY);
            filterStatus.addValueChangeListener(e -> listRequests(e.getValue()));



        HorizontalLayout menuBar = new HorizontalLayout(filterApartment, filterArea, filterDate, filterStatus, refresh);
        add(menuBar, grid, editorStaff);

        grid.asSingleSelect().addValueChangeListener(e -> {
            editorStaff.edit(e.getValue());
        });

        refresh.addClickListener(e -> listRequests(filterApartment.getValue()));
        refresh.addClickListener(e -> editorStaff.setVisible(false));

    }
    private void listRequests(String filter) {
        //I'll start documentation here because this is where it actually gets meaty.
        //Textfield scans for what user inputs.
        if(filter != null && !filter.isEmpty()){
            if(!filterApartment.isEmpty() && filterArea.isEmpty()
                    && filterStatus.isEmpty() && filterDate.isEmpty()){ //Makes sure that only apartment has text
                grid.setItems(repository.findByApartNum(Integer.parseInt(filter)));
            }
            else if(filterApartment.isEmpty() && !filterArea.isEmpty()
                    && filterStatus.isEmpty() && filterDate.isEmpty()) { //Only area has text
                grid.setItems(repository.findByArea(filter));
            }else if(filterApartment.isEmpty() && filterArea.isEmpty()
                    && !filterStatus.isEmpty() && filterDate.isEmpty()) { //Only status has text
                grid.setItems(repository.findByStatus(filter));
            }else if(filterApartment.isEmpty() && filterArea.isEmpty()
                    && filterStatus.isEmpty() && !filterDate.isEmpty()) { //Only date has text
                grid.setItems(repository.findByDate(filter));
            }
            else{
                grid.setItems(repository.findByStatus(filter));// Otherwise, searches by status.
            }
        }else{
            grid.setItems((Collection<MaintReq>) repository.findAll());//If the filter box is empty, puts the entire list.
        }
    }
}
