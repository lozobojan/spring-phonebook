package com.lozo.phonebook.storage;

import com.lozo.phonebook.contact.Contact;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    private String uploadPath = "C:\\Users\\Lozo\\Desktop\\SPRING\\phonebook\\uploads\\";

    public String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    @Transactional
    public void uploadFile(MultipartFile file, Contact contact) throws IOException {
        String newFilename = UUID.randomUUID().toString()+"."+this.getExtension(file.getOriginalFilename());
        file.transferTo(new File(this.uploadPath+newFilename));
        contact.setProfilePhoto(newFilename);
    }


}
