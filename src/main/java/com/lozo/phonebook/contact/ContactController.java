package com.lozo.phonebook.contact;

import com.lozo.phonebook.storage.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/contacts")
public class ContactController {

    private ContactRepository contactRepository;
    ContactService contactService = new ContactService(contactRepository);

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts(){
        return contactService.getContacts();
    }

    @PostMapping
    public void saveNewContact(
                                // ovako mogu sve u JSON odjednom
                               // @RequestBody Contact contact,
                               @RequestParam(name = "firstName", required = false) String firstName,
                               @RequestParam(name = "lastName", required = false) String lastName,
                               @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(name = "emailAddress", required = false) String emailAddress,
                               @RequestParam(name = "file") MultipartFile file) throws IOException {
        Contact newContact = new Contact(firstName,lastName,phoneNumber,emailAddress);
        contactService.addNewContact(newContact);
        fileUploadService.uploadFile(file, newContact);
    }

    @PutMapping(path = "{contactId}")
    public void updateContact(
            @PathVariable(name = "contactId") Long contactId,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "emailAddress", required = false) String emailAddress
    ){
        contactService.updateContact(contactId,firstName,lastName,phoneNumber,emailAddress);
    }

    @DeleteMapping(path = "{contactId}")
    public void deleteContact(@PathVariable(name = "contactId") Long contactId){
        contactService.deleteContact(contactId);
    }

    @RequestMapping(
            value = "{contactId}/profilePhoto",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("contactId") Long contactId) {
        Optional<Contact> contact = contactService.getContactById(contactId);
        if(!contact.isPresent()){
            throw new IllegalStateException("The contact with id "+contactId+" doesn't exist!");
        }
        String profilePhoto = "nophoto.png";
        if(contact.get().getProfilePhoto() != null){
            profilePhoto = contact.get().getProfilePhoto();
        }
        return new FileSystemResource("uploads\\"+profilePhoto);
    }
}
