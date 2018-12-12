package com.test.service;

import com.test.po.MiguZqSong;

public interface MiguZqSongService {

	int selectSongById(String songId);

	int saveSong(MiguZqSong miguZqSong);

	int deleteSong(String songId);
	
	public void saveTrans();

}
