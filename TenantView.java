package com.example.project2;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Route
public class TenantView extends VerticalLayout {

    private final Button add;
    private final TenantEditor editor;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    public TenantView (TenantEditor editor){
        this.editor = editor;
        this.add = new Button("New request", VaadinIcon.PLUS.create());
        HorizontalLayout menuBar = new HorizontalLayout(add);
        add(menuBar, editor);

        add.addClickListener(e -> editor.edit(new MaintReq(0, "", "", date, "", "pending")));
    }
}
