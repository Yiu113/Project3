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

@SpringComponent
@UIScope
public class Editor  extends VerticalLayout implements KeyNotifier {
    private final TenantRepository repository;
    private Tenant tenant;
    TextField name = new TextField("Name");
    TextField email = new TextField("Email");

    TextField phoneNum = new TextField("Phone Number");

    TextField checkIn = new TextField("Check In Date:");

    TextField checkout = new TextField("Check Out Date:");

    TextField apartmentNum = new TextField("Apartment Number:");


    Button add = new Button("Add", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");

    Button delete = new Button("Delete", VaadinIcon.TRASH.create());

    HorizontalLayout buttons = new HorizontalLayout(add, cancel, delete);
    Binder<Tenant> binder = new Binder<>(Tenant.class);

    public Editor(TenantRepository repository){
        this.repository = repository;
        add(name, email, phoneNum, checkIn, checkout, apartmentNum, buttons);
        binder.bindInstanceFields(this);
        setSpacing(true);

        add.addClickListener(e -> add());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> edit(tenant));
        setVisible(false);
    }

    void add(){
        repository.save(tenant);
    }
    void delete(){
        repository.delete(tenant);
    }
    void edit(Tenant e){
        if (e == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = e.getId() != null;
        if (persisted) {
            tenant = repository.findById(e.getId()).get();
        }
        else {
            tenant = e;
        }
        cancel.setVisible(persisted);
        binder.setBean(tenant);

        setVisible(true);
        name.focus();
    }//Tried making this auto-update after you click a button but it didn't work.
}
