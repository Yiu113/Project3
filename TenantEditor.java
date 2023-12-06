package com.example.project2;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class TenantEditor extends VerticalLayout implements KeyNotifier {

               // "Request[id=%d, apartment number='%d', description='%s', date='%s', photo='%s', status='%s']",
    private final RequestRepository repository;
    private MaintReq request;

    TextField apartNum = new TextField("Apartment Number");
    TextField area = new TextField("Area of Problem");
    TextField description = new TextField("Description of Problem");
    TextField photo = new TextField("Photo of Problem");
    Button add = new Button("Add", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    HorizontalLayout buttons = new HorizontalLayout(add, cancel);
    Binder<MaintReq> binder = new Binder<>(MaintReq.class);

    public TenantEditor(RequestRepository repository){
        this.repository = repository;
        add(apartNum, area, description, photo, buttons);
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
    }//Tried making this auto-update after you click a button but it didn't work.

}
