package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column(name = "person_id")
    //private int person_id;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")//, insertable=false, updatable=false)
    private Person owner;
    @Column(name = "item_name")
    private String item_name;

    public Item(){}

    public Item(String item_name) {
        this.item_name = item_name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //public int getPerson_id() {
    //    return person_id;
    //}
//
    //public void setPerson_id(int person_id) {
       // this.person_id = person_id;
    //}

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public String toString() {
        return this.item_name;// + " ordered by " + this.person_id;
    }
}
