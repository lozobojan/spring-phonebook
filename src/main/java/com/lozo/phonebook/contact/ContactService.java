package com.lozo.phonebook.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public void addNewContact(Contact contact){
        contactRepository.save(contact);
    }

    @Transactional
    public void updateContact(Long contactId, String firstName, String lastName, String phoneNumber, String emailAddress){
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new IllegalStateException("The contact with ID: "+contactId+" doesn't exist! ")
        );
        // !Objects.equals(contact.getFirstName(), firstName)
        if(firstName != null && firstName.length() > 0 && contact.getFirstName() != firstName ){
            contact.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && contact.getLastName() != lastName ){
            contact.setLastName(lastName);
        }
        if(phoneNumber != null && phoneNumber.length() > 0 && contact.getPhoneNumber() != phoneNumber ){
            contact.setPhoneNumber(phoneNumber);
        }
        if(emailAddress != null && emailAddress.length() > 0 && contact.getEmailAddress() != emailAddress ){
            contact.setEmailAddress(emailAddress);
        }
    }

    public void deleteContact(Long contactId){
        boolean exists = contactRepository.existsById(contactId);
        if(!exists){
            throw new IllegalStateException("The contact with id "+contactId+" doesn't exist!");
        }else{
            contactRepository.deleteById(contactId);
        }
    }

    public Optional<Contact> getContactById(Long contactId){
        return contactRepository.findById(contactId);
    }
}
