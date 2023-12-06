package com.example.project2;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.awt.*;

@SpringComponent
@UIScope
public class StaffEditor extends VerticalLayout implements KeyNotifier {
    private final RequestRepository repository;
    private MaintReq request;
    TextField status = new TextField("Status:");
    Button cancel = new Button("Cancel");
    Button add = new Button("Add", VaadinIcon.CHECK.create());
    HorizontalLayout buttons = new HorizontalLayout(add, cancel);
    Binder<MaintReq> binder = new Binder<>(MaintReq.class);

    public StaffEditor(RequestRepository repository){
        this.repository = repository;
        add(status, buttons);
        binder.bindInstanceFields(this);
        setSpacing(true);

        add.addClickListener(e -> add());
        cancel.addClickListener(e -> edit(request));
        setVisible(false);
    }

    void add(){
        repository.save(request);
    }
    void edit(MaintReq e){
        if (e == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = e.getID() != null;
        if (persisted) {
            request = repository.findById(e.getID()).get();
        }
        else {
            request = e;
        }
        cancel.setVisible(persisted);
        binder.setBean(request);

        setVisible(true);
        status.focus();
    }//Tried making this auto-update after you click a button but it didn't work.
}
