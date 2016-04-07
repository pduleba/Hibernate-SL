package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.News;
import com.pduleba.hibernate.model.User;

public interface NewsService {

	News getNewsById(long id);

	void addNews(News news);

	void updateNews(News news);

	void deleteNews(News news);

	List<News> getAllNews();
	
	User getUserByNewsId(long id);
}
