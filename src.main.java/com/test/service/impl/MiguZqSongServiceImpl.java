package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.MiguZqSongDao;
import com.test.po.MiguZqSong;
import com.test.service.MiguZqSongService;

@Service("MiguZqSongService")
public class MiguZqSongServiceImpl implements MiguZqSongService {

	@Autowired
	private MiguZqSongDao miguZqSongDao;

	@Override
	public int selectSongById(String songId) {
		return miguZqSongDao.selectSongById(songId);
	}

	@Override
	public int saveSong(MiguZqSong miguZqSong) {
		return miguZqSongDao.saveSong(miguZqSong);
	}

	@Override
	public int deleteSong(String songId) {
		return miguZqSongDao.deleteSong(songId);
	}

	@Override
	public void saveTrans() {
		MiguZqSong miguZqSong = new MiguZqSong();
		miguZqSong.setSongName("aaaa");
		int delNum = miguZqSongDao.deleteSong("10041");
		int addNum = miguZqSongDao.saveSong(miguZqSong);
		System.out.println(delNum + ":" + addNum);
	}

}
