package com.test.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.test.po.MiguZqSong;

@Repository
public interface MiguZqSongDao {

	int selectSongById(@Param("songId") String songId);

	int saveSong(@Param(value = "song")MiguZqSong miguZqSong);

	int deleteSong(@Param("songId") String songId);

}
