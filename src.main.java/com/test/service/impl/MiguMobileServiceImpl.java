package com.test.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.MiguMobileSegDao;
import com.test.po.MiguMobileSeg;
import com.test.service.MiguMobileService;

@Service("MiguMobileService")
public class MiguMobileServiceImpl implements MiguMobileService {

	@Autowired
	private MiguMobileSegDao miguMobileSegDao;

	@Override
	public void readTxt() {
		MiguMobileSeg miguMobileSeg = new MiguMobileSeg();
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
				miguMobileSeg.setProvince(strArr[0]);
				miguMobileSeg.setCity(strArr[1]);
				miguMobileSeg.setCode(strArr[2]);
				miguMobileSeg.setSeg(strArr[3]);
				MiguMobileSeg m = miguMobileSegDao.select(miguMobileSeg
						.getSeg());
				if (m == null) {// 新增
					int i = miguMobileSegDao.insert(miguMobileSeg);
					System.out.println("新增：" + i);
				} else { // 更新
					int i = miguMobileSegDao.update(miguMobileSeg);
					System.out.println("更新：" + i);
				}
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

}
