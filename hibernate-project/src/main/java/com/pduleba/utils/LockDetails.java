package com.pduleba.utils;

import java.util.concurrent.CountDownLatch;

import org.hibernate.LockMode;
import org.slf4j.Logger;

import lombok.Data;

public @Data class LockDetails {

	private LockMode lockMode;
	private int sleepTime;
	private CountDownLatch startFlag;
	private Logger log;
	private int interval;

	public LockDetails(LockMode lockMode, int sleepTime, int interval, CountDownLatch startFlag, Logger log) {
		this.lockMode = lockMode;
		this.sleepTime = sleepTime;
		this.interval = interval;
		this.startFlag = startFlag;
		this.log = log;
	}

}
