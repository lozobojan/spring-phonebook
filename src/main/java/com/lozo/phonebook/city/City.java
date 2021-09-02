package com.lozo.phonebook.city;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lozo.phonebook.contact.Contact;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "city")
public class City {

    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )
    private Long id;
    private String name;

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public City(String name) {
        this.name = name;
    }
    public City() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "city")
    @JsonBackReference
    private Collection<Contact> contacts;

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }
}
