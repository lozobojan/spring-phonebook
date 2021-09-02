package com.lozo.phonebook.city;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CityConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerCities (CityRepository cityRepository){
        return args->{
            City pg = new City(
                    "Podgorica"
            );
            City bd = new City(
                    "Budva"
            );
            City dg = new City(
                    "Danilovgrad"
            );
            // dodao sam gradove u ContactConfiguration
            // cityRepository.saveAll(List.of(bd, pg, dg));
        };
    }

}
