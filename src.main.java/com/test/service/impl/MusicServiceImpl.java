package com.test.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.MusicDao;
import com.test.po.ProductFile;
import com.test.service.MusicService;
import com.test.util.ThreadPool;

@Service("musicService")
public class MusicServiceImpl implements MusicService {

	@Autowired
	private MusicDao musicDao;

	@Override
	public int selectMusicById(String musicId) {
		readTxt();
		return musicDao.selectMusicById(musicId);
	}

	@Override
	public Map<String, Object> selectProductByCopyrightId(String copyrightId,
			String productId) {
		return musicDao.selectProductByCopyrightId(copyrightId, productId);
	}

	@Override
	public void testThreadPool(String name) {
		// ScheduledExecutorService executorService = new
		// ScheduledThreadPoolExecutor(
		// 3);
		// executorService.scheduleAtFixedRate(new AsyncRunnable(name), 1, 1,
		// TimeUnit.SECONDS);
		// executorService.scheduleWithFixedDelay(new AsyncRunnable(name+"1"),
		// 1, 1, TimeUnit.SECONDS);
		// executorService.schedule(new AsyncRunnable(name+"2"), 3,
		// TimeUnit.SECONDS);
		// executorService.shutdown();
		for (int i = 0; i < 10; i++) {
			ThreadPool.execute(new AsyncRunnable(i + ""));
		}
	}

	@Override
	public void data() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("D://llsong3.txt"), "UTF-8"));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				String[] strArr = tempString.split("\t");
				String copyrightId = strArr[0];
				String productId2 = strArr[1];
				String productIdE = strArr[2];
				System.out.println(copyrightId);
				List<ProductFile> productFile2 = musicDao.selectProductFile(
						copyrightId + "2", productId2);
				List<ProductFile> productFileE = musicDao.selectProductFile(
						copyrightId + "E", productIdE);
				if (productFile2 == null || productFile2.isEmpty()) {
					for (ProductFile productFile : productFileE) {
						productFile.setCopyrightId(strArr[0] + "2");
						productFile.setProductId(strArr[1]);
						// musicDao.saveProductFile(productFile);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readTxt() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("D://seg.txt"), "UTF-8"));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				String[] strArr = tempString.split("\t");
				System.out.println(strArr[0] + "','" + strArr[1] + "',"
						+ strArr[2] + "," + strArr[3]);
				String out = "insert into migu_mobile_seg (id,province,city,code,seg)"
						+ " values(seq_migu_mobile_seg.nextval,'"
						+ strArr[0]
						+ "','"
						+ strArr[1]
						+ "','"
						+ strArr[2]
						+ "','"
						+ strArr[3] + "');";
				writerTxt(out);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writerTxt(String out) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File(
					"D://seg_insert.txt"), true));
			writer.write("\n" + out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class AsyncRunnable implements Runnable {

		private String name;

		public AsyncRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			// Lock lock = new ReentrantLock();
			// lock.lock();
			try {
				System.out.println(Thread.currentThread() + ":" + name);
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// lock.unlock();
			}
		}

	}

}
