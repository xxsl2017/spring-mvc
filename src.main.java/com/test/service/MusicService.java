package com.test.service;

import java.util.Map;

public interface MusicService {
	
	int selectMusicById(String musicId);
	
	Map<String, Object> selectProductByCopyrightId(String copyrightId,String productId);
	
	void data();
	
	void testThreadPool(String name);
}
