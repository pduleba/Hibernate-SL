package com.pduleba.spring.dao;

import java.util.List;

public interface AbstractDao<T> {

	void create(T enity);

	T read(long entityId);

	void update(T enity);

	void delete(T enity);

	void createAll(List<T> entities);

}
