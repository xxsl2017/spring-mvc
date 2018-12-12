package com.test.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.po.MiguZqSong;
import com.test.po.Person;
import com.test.service.MiguZqSongService;
import com.test.service.MusicService;

@Controller
@RequestMapping("")
public class TestController {

	private final static Logger logger = LoggerFactory
			.getLogger(TestController.class);

	@Autowired
	private MusicService musicService;
	@Autowired
	private MiguZqSongService miguZqSongService;

	@RequestMapping("test01")
	public String test01(String name) {
		//musicService.testThreadPool(name);
		// musicService.data();
		musicService.selectMusicById("11");
		//System.out.println("aaaaaaa");
		return "test01";
	}

	@RequestMapping("test02")
	public ModelAndView test02(String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test02");
		modelAndView.addObject("name", name);
		return modelAndView;
	}

	@RequestMapping("test03")
	public void test03(String name) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("D://zz/"+name+".dat"), "utf-8"));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				System.out.println(tempString);
				String[] strArr = tempString.split("@");
				String type = strArr[7];
				if("00".equals(type)){
					MiguZqSong miguZqSong = new MiguZqSong();
					miguZqSong.setSongName(strArr[1].substring(0,10));
					miguZqSong.setPhoneNumber(strArr[2]);
					miguZqSongService.saveSong(miguZqSong);
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
		//System.out.println(name);
	}

	@RequestMapping("test04")
	public Object test04(String name) {
		System.out.println(name);
		return "test04";
	}

	@RequestMapping("test05")
	@ResponseBody
	public Person test05(String name, String musicId) {
		logger.info("22222");
		Map<String, Object> map = new HashMap<>();
		map.put("name", name + "11");
		Person person = new Person();
		person.setName(name);
		int age = musicService.selectMusicById(musicId);
		person.setAge(age);
		return person;
	}

	@RequestMapping("test06")
	@ResponseBody
	public void test06() {
		miguZqSongService.saveTrans();
	}
	
	
	@RequestMapping("test07")
	public void test07(String name) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("D://"+name), "utf-8"));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				System.out.println(tempString);
				String[] strArr = tempString.split("\t");
				String copyrightId = strArr[0];
				String productId = strArr[1];
//				String copyrightId = strArr[1].substring(0, 12);
//				String productId = strArr[1].substring(12);
//				String status = strArr[2];
//				String date = strArr[3];
//				String out = copyrightId+" "+productId+" ";
				if("无产品".endsWith(strArr[2].trim())){
					writerTxt("a_"+name,tempString+"\n");
				}else{
				Map<String, Object> map = musicService.selectProductByCopyrightId(copyrightId, productId);
				if(map!=null){
					writerTxt("a_"+name,tempString+"\n");
				}
//				if(map==null){
//					out = out + "无产品";
//				}else{
//					String edate = map.get("EDATE")==null?"":map.get("EDATE").toString();
//					String estatus = map.get("STATUS")==null?"":map.get("STATUS").toString();
//					if(!status.equals(estatus)&&!date.equals(edate)){
//						out = out + "状态及有效期不同";
//					} else if(!status.equals(estatus)){
//						out = out + "状态不同";
//					} else if(!date.equals(edate)){
//						out = out + "有效期不同";
//					} else {
//						out = "";
//					}
//				}
//				if(!"".equals(out)){
//					out = out + "\n";
//					writerTxt("a_"+name,out);
//				}
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
	
	public void writerTxt(String name,String out) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File(
					"D://"+name), true));
			writer.write(out);
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

}
