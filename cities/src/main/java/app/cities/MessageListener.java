package app.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageListener
{
    @RabbitListener(queues = CitiesApplication.SECRET)
    public void receiveSecret(CityMessage message)
    {
        log.info("Received Secret Message {}", message.toString());
    }

    @RabbitListener(queues = CitiesApplication.QUEUE_CITIES_1)
    public void receiveCities1(CityMessage message)
    {
        log.info("Received City1 Message {}", message.toString());
    }

    @RabbitListener(queues = CitiesApplication.QUEUE_CITIES_2)
    public void receiveCities2(CityMessage message)
    {
        log.info("Received City2 Message {}", message.toString());
    }
}
