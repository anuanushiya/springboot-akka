package it.r.controller;

import static akka.pattern.Patterns.ask;
import akka.actor.ActorRef;
import it.r.akka.message.IncrementCounter;
import it.r.akka.message.Read;
import it.r.akka.spring.SpringActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;


/**
 * Created by rascioni on 20/01/15.
 */
@RestController
public class HelloController {

    private final ActorRef counterActor;

    @Autowired
    public HelloController(SpringActorSystem springActorSystem) {
        this.counterActor = springActorSystem.actorOf("CounterActor", "counter");
    }

    @RequestMapping("/")
    public String index (){
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.GET, value="/counter")
    public String read() throws Exception {
        return String.format("You hit %s times the page", Await.result(ask(counterActor, new Read(), 100), Duration.apply(1, TimeUnit.SECONDS)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/counter")
    public String increment(){
        counterActor.tell(new IncrementCounter(), null);
        return "Done.";
    }
}
