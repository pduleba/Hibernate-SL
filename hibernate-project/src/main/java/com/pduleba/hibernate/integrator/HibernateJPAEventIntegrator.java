package com.pduleba.hibernate.integrator;

import org.hibernate.boot.Metadata;
import org.hibernate.cfg.beanvalidation.DuplicationStrategyImpl;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.DuplicationStrategy;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import com.pduleba.hibernate.listeners.HibernateEventListener;

public class HibernateJPAEventIntegrator implements Integrator {

    private HibernateEventListener basicEventListener = new HibernateEventListener();
	private DuplicationStrategy duplicationStrategy = new DuplicationStrategyImpl();

	public void integrate(
    		Metadata metadata,
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
    	
        // As you might expect, an EventListenerRegistry is the thing with which event listeners are registered  It is a
        // service so we look it up using the service registry
        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
        
		// If you wish to have custom determination and handling of "duplicate" listeners, you would have to add an
        // implementation of the org.hibernate.event.service.spi.DuplicationStrategy contract like this
        eventListenerRegistry.addDuplicationStrategy( duplicationStrategy  );

        // EventListenerRegistry defines 3 ways to register listeners:
        //     1) This form overrides any existing registrations with
        eventListenerRegistry.setListeners( EventType.PRE_INSERT, basicEventListener );
        eventListenerRegistry.setListeners( EventType.POST_INSERT, basicEventListener );
        eventListenerRegistry.setListeners( EventType.PRE_LOAD, basicEventListener );
        eventListenerRegistry.setListeners( EventType.POST_LOAD, basicEventListener );
        eventListenerRegistry.setListeners( EventType.PRE_UPDATE, basicEventListener );
        eventListenerRegistry.setListeners( EventType.POST_UPDATE, basicEventListener );
        eventListenerRegistry.setListeners( EventType.PRE_DELETE, basicEventListener );
        eventListenerRegistry.setListeners( EventType.POST_DELETE, basicEventListener );
        //     2) This form adds the specified listener(s) to the beginning of the listener chain
//		eventListenerRegistry.prependListeners( EventType.POST_LOAD, basicEventListener );
		//     3) This form adds the specified listener(s) to the end of the listener chain
//		eventListenerRegistry.appendListeners( EventType.POST_LOAD, basicEventListener );
    }
    
    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    	// TODO Auto-generated method stub
    }
}