package com.pduleba.hibernate.listeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAEventListener {
	
	public static final Logger LOG = LoggerFactory.getLogger(JPAEventListener.class);
	
	@PostLoad
	public void postLoad(Object model) {
		showMe("@postLoad", model);
	}

	@PrePersist
	public void prePersist(Object model) {
		showMe("@prePersist", model);
	}
	
	@PostPersist
	public void postPersist(Object model) {
		showMe("@postPersist", model);
	}
	
	@PreUpdate
	public void preUpdate(Object model) {
		showMe("@preUpdate", model);
	}
	
	@PostUpdate
	public void postUpdate(Object model) {
		showMe("@postUpdate", model);
	}
	
	@PreRemove
	public void preRemove(Object model) {
		showMe("@preUpdate", model);
	}
	
	@PostRemove
	public void postRemove(Object model) {
		showMe("@postUpdate", model);
	}
	
	private void showMe(String mode, Object model) {
		LOG.info("{} :: {}", mode, model);
	}
}
