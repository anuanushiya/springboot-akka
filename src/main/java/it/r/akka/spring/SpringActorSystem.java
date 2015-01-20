package it.r.akka.spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import it.r.akka.SpringExtensionId;

/**
 * Created by rascioni on 20/01/15.
 */
public class SpringActorSystem {

    private final ActorSystem actorSystem;

    public SpringActorSystem(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    public ActorRef actorOf(String beanName, String actorName) {
        return actorSystem.actorOf(SpringExtensionId.PROVIDER.get(actorSystem).props(beanName), actorName);
    }
}
