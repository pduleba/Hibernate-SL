package com.pduleba.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.News;
import com.pduleba.hibernate.model.User;
import com.pduleba.spring.dao.NewsDao;

@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	NewsDao newsDao;

	@Override
	public News getNewsById(long id) {
		return newsDao.getNewsById(id);
	}

	@Override
	public void addNews(News news) {
		newsDao.addNews(news);
	}

	@Override
	public void updateNews(News news) {
		News newsToUpdate = getNewsById(news.getIdNews());
		newsToUpdate.setTitle(news.getTitle());
		newsToUpdate.setBody(news.getBody());
		newsToUpdate.setUploadDate(news.getUploadDate());
		newsToUpdate.setUser(news.getUser());
		newsDao.updateNews(newsToUpdate);
	}

	@Override
	public void deleteNews(News news) {
		newsDao.deleteNews(news);
	}

	@Override
	public List<News> getAllNews() {
		return newsDao.getAllNews();
	}

	@Override
	public User getUserByNewsId(long id) {
		return newsDao.getUserByNewsId(id);
	}

}
