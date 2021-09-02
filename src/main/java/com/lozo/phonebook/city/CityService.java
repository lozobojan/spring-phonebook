package com.lozo.phonebook.city;

import com.lozo.phonebook.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }

}
