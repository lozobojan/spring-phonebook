package com.lozo.phonebook.city;

import com.lozo.phonebook.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/cities")
public class CityController {

    CityRepository cityRepository;
    CityService cityService = new CityService(cityRepository);

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getCities(){
        return cityService.getCities();
    }
}
