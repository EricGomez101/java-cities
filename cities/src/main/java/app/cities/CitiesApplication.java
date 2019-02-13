package app.cities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitiesApplication
{
    public static final String EXCHANGE_NAME = "LambdaServer";
    public static final String SECRET = "secret";
    public static final String QUEUE_CITIES_1 = "Cities1";
    public static final String QUEUE_CITIES_2 = "Cities2";


    public static void main(String[] args)
    {

        SpringApplication.run(CitiesApplication.class, args);
    }


    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueSecret()
    {
        return new Queue(SECRET);
    }

    @Bean
    public Queue appQueueCities1()
    {
        return new Queue(QUEUE_CITIES_1);
    }

    @Bean
    public Queue appQueueCities2()
    {
        return new Queue(QUEUE_CITIES_2);
    }

    @Bean
    public Binding bindSecretToExchanger()
    {
        return BindingBuilder.bind(appQueueSecret()).to(appExchange()).with(SECRET);
    }

    @Bean
    public Binding bindCities1ToExchanger()
    {
        return BindingBuilder.bind(appQueueCities1()).to(appExchange()).with(QUEUE_CITIES_1);
    }

    @Bean
    public Binding bindCities2ToExchange()
    {
        return BindingBuilder.bind(appQueueCities2()).to(appExchange()).with(QUEUE_CITIES_2);
    }


    @Bean
    public Jackson2JsonMessageConverter producer()
    {
        return new Jackson2JsonMessageConverter();
    }

}

