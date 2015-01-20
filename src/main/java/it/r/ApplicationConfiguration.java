package it.r;

import akka.actor.ActorSystem;
import it.r.akka.SpringExtensionId;
import it.r.akka.spring.SpringActorSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rascioni on 20/01/15.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationConfiguration {

    @Bean
    public ActorSystem actorSystem(ApplicationContext applicationContext){
        final ActorSystem actorSystem = ActorSystem.create("SpringApplication");
        SpringExtensionId.PROVIDER.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public SpringActorSystem springActorSystem(ActorSystem actorSystem) {
        return new SpringActorSystem(actorSystem);
    }

    public static void main(String[] args){
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
