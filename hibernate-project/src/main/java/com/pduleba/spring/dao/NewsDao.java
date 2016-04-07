package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.News;
import com.pduleba.hibernate.model.User;

public interface NewsDao {
	
    void addNews(News news);
    
    void deleteNews(News news);
     
    List<News> getAllNews();
 
    News getNewsById(long id);
    
    void updateNews(News news);

	void deleteAllNews();
	
	User getUserByNewsId(long id);
}
