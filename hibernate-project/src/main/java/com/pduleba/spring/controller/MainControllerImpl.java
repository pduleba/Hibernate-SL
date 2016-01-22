package com.pduleba.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.spring.services.OwnerService;
import com.pduleba.spring.services.UtilityService;
import com.pduleba.spring.services.UtilityService.Mode;

@Component
public class MainControllerImpl implements MainController {

	public static final Logger LOG = LoggerFactory.getLogger(MainControllerImpl.class);
	
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private UtilityService utils;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;

	@Override
	public void execute() {
		LOG.info("------------");
		Long ownerId = create();
		LOG.info("------------");
		OwnerModel persisted = read(ownerId);
		LOG.info("------------");
		update(persisted, Thread.currentThread().getName());
		LOG.info("------------");
		delete(persisted);
		LOG.info("------------");
	}
	
	private Long create() {
		List<OwnerModel> owners = utils.getData();
		utils.show(owners, Mode.CREATE);
		ownerService.createAll(owners);
		
		return owners.get(0).getId();
	}

	private OwnerModel read(long ownerId) {
		OwnerModel owner = ownerService.read(ownerId);
		utils.show(owner, Mode.READ);
		
		return owner;
	}

	private void update(OwnerModel owner, String newName) {
		owner.setFirstName(newName);
		utils.show(owner, Mode.UPDATE);
		ownerService.update(owner);
	}

	private void delete(OwnerModel owner) {
		if (deleteEnabled) {
			ownerService.delete(owner);
			OwnerModel deleted = ownerService.read(owner.getId());
			utils.show(deleted, Mode.DELETE);
		} else {
			LOG.warn("Delete feature disabled!");
		}
	}
}
