package it.r.akka.spring;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * Created by rascioni on 20/01/15.
 */
public class SpringExtensionId extends AbstractExtensionId<SpringExtensionId.SpringExtension>{

    public static final SpringExtensionId PROVIDER = new SpringExtensionId();

    @Override
    public SpringExtension createExtension(ExtendedActorSystem system) {
        return new SpringExtension();
    }

    public static class SpringExtension implements Extension {

        private ApplicationContext context;

        public void initialize(ApplicationContext context) {
            this.context = context;
        }

        public Props props(String beanName) {
            return Props.create(SpringActorProducer.class, context, beanName);
        }
    }
}
