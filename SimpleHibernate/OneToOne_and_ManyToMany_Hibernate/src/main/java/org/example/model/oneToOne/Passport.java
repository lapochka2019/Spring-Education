package org.example.model.oneToOne;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Passport")
public class Passport implements Serializable {
    @Id
    @OneToOne
    @JoinColumn (name = "person_id", referencedColumnName = "id")
    private Person person;
    @Column(name = "passport_number")
    private int passport_number;

    public Passport(){

    }

    public Passport(Person person, int passport_number) {
        this.person = person;
        this.passport_number = passport_number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(int passport_number) {
        this.passport_number = passport_number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "person=" + person +
                ", passport_number=" + passport_number +
                '}';
    }
}
