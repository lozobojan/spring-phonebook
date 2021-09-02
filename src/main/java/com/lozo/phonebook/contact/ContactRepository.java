package com.lozo.phonebook.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // @Query("SELECT s FROM Contact c WHERE c.emailAddress = ?1");
//    Optional<Contact> findContactByEmailAddress(String emailAddress);

}
