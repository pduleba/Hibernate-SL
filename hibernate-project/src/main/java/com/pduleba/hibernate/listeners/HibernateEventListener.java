package com.pduleba.hibernate.listeners;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateEventListener
		implements PreLoadEventListener, PostLoadEventListener, PreUpdateEventListener, PostUpdateEventListener,
		PreInsertEventListener, PostInsertEventListener, PreDeleteEventListener, PostDeleteEventListener {

	private static final long serialVersionUID = -6675782075278818812L;
	
	public static final Logger LOG = LoggerFactory.getLogger(HibernateEventListener.class);

	@Override
	public void onPreLoad(PreLoadEvent event) {
		showMe("onPreLoad()", event.getEntity());
	}
	
	@Override
	public void onPostLoad(PostLoadEvent event) {
		showMe("onPostLoad()", event.getEntity());
	}
	
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		showMe("onPreInsert()", event.getEntity());
		return false;
	}
	
	@Override
	public void onPostInsert(PostInsertEvent event) {
		showMe("onPostInsert()", event.getEntity());
	}
	
	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		showMe("onPreUpdate()", event.getEntity());
		return false;
	}
	
	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		showMe("onPostUpdate()", event.getEntity());
	}
	
	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		showMe("onPreDelete()", event.getEntity());
		return false;
	}
	
	@Override
	public void onPostDelete(PostDeleteEvent event) {
		showMe("onPostLoad()", event.getEntity());
	}
	
	private void showMe(String mode, Object model) {
		LOG.info("{} :: {}", mode, model);
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return true;
	}
}
