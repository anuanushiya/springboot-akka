package it.r.akka.actor;

import akka.actor.UntypedActor;
import it.r.akka.message.IncrementCounter;
import it.r.akka.message.Read;
import it.r.akka.spring.SpringActor;
import javax.inject.Named;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rascioni on 20/01/15.
 */
@SpringActor
@Named("CounterActor")
public class CounterActor extends UntypedActor{

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof IncrementCounter) {
            count.incrementAndGet();
        }
        else if (message instanceof Read) {
            getSender().tell(new Integer(count.get()), getSelf());
        }
        else {
            unhandled(message);
        }
    }
}
