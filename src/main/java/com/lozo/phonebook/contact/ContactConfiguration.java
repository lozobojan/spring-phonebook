package com.lozo.phonebook.contact;

import com.lozo.phonebook.city.City;
import com.lozo.phonebook.city.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ContactConfiguration {

    @Bean
    CommandLineRunner commandLineRunner (ContactRepository contactRepository, CityRepository cityRepository){

        return args->{
            // prvo dodajem gradove
            City pg = new City("Podgorica");
            City bd = new City("Budva");
            City dg = new City("Danilovgrad");
            cityRepository.saveAll(List.of(bd, pg, dg));

            Contact marko = new Contact(
                    "Marko",
                    "Markovic",
                    "069/888-555",
                    "marko@mail.com",
                    null,
                    bd
            );

            Contact janko = new Contact(
                    "Janko",
                    "Jankovic",
                    "069/444-000",
                    "janko@gmail.com",
                    null,
                    bd
            );

            Contact bojan = new Contact(
                    "Bojan",
                    "Lozo",
                    "067/363-573",
                    "lozobojan@gmail.com",
                    null,
                    pg
            );
            contactRepository.saveAll(List.of(marko, janko, bojan));
        };
    }

}
