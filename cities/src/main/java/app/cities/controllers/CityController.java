package app.cities.controllers;

import app.cities.CitiesApplication;
import app.cities.CityMessage;
import app.cities.models.City;
import app.cities.repositorys.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/cities")
public class CityController
{
    private final CityRepository cityrepos;
    private final RabbitTemplate rabbitTemplate;

    public CityController(CityRepository cityrepos, RabbitTemplate rabbitTemplate)
    {
        this.cityrepos = cityrepos;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/afford")
    public void afford()
    {
        ArrayList<City> citys = new ArrayList<>();
        citys.addAll(cityrepos.findAll());

        for (City c: citys)
        {
            int index = c.getIndex();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), index, secret);

            if (secret)
            {
                rabbitTemplate.convertAndSend(CitiesApplication.SECRET, message);
            } else if (index < 6)
            {
                rabbitTemplate.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            } else
            {
                rabbitTemplate.convertAndSend(CitiesApplication.QUEUE_CITIES_2, message);
            }
        }
    }

    @GetMapping("/homes")
    public void homes()
    {
        ArrayList<City> citys = new ArrayList<>();
        citys.addAll(cityrepos.findAll());

        for (City c: citys)
        {
            int index = c.getMedianPrice();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), index, secret);

            if (secret)
            {
                rabbitTemplate.convertAndSend(CitiesApplication.SECRET, message);
            } else if ( index > 200000)
            {
                rabbitTemplate.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            } else
            {
                rabbitTemplate.convertAndSend(CitiesApplication.QUEUE_CITIES_2, message);
            }
        }
    }

    @GetMapping("/names")
    public void names()
    {
        ArrayList<City> citys = new ArrayList<>();
        citys.addAll(cityrepos.findAll());

        for (City c: citys)
        {
            int index = c.getMedianPrice();
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), index, secret);

            if (secret)
            {
                rabbitTemplate.convertAndSend(CitiesApplication.SECRET, message);
            } else
            {
                rabbitTemplate.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            }
        }
    }
}
