package com.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.test.po.ProductFile;

@Repository
public interface MusicDao {
	
	int selectMusicById(@Param("musicId") String musicId);
	
	Map<String,Object> selectProductByCopyrightId(@Param("copyrightId") String copyrightId,
			@Param("productId") String productId);
	
	List<ProductFile> selectProductFile(@Param("copyrightId") String copyrightId,
			@Param("productId") String productId);
	
	int saveProductFile(ProductFile productFile);

}
