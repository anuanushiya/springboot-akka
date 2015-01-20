package it.r.akka.spring;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Created by rascioni on 20/01/15.
 */
public class SpringActorProducer implements IndirectActorProducer{

    private final ApplicationContext applicationContext;
    private final String beanName;


    public SpringActorProducer(ApplicationContext applicationContext, String beanName) {
        this.applicationContext = applicationContext;
        this.beanName = beanName;
    }


    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(beanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanName);
    }
}
