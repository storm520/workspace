package com.taotao.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.until.FtpUtil;
import com.taotao.common.until.IDUtils;
import com.taotao.service.pictureService;

@Service
public class picServiceIml implements pictureService {
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_PATH}")
	private String IMAGE_BASE_PATH;

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		String imagePath = new DateTime().toString("/yyyy/MM/dd");
		try {
			//取一个新的名字(取扩展名)
			//得到旧名字
			String oldname = uploadFile.getOriginalFilename();
			//新的名字
			String newname = IDUtils.genImageName();
			newname = newname+oldname.substring(oldname.indexOf("."));
			//上传文件
			boolean result=FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FTP_BASE_PATH, imagePath, newname, uploadFile.getInputStream());
			System.out.println(result);
			//System.out.println(FTP_BASE_PATH);
			//返回结果
			if(!result)
			{
				resultMap.put("error", 1);
				resultMap.put("message", "上传失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_PATH+imagePath+"/"+newname);
			return resultMap;
		}catch(Exception e){
			resultMap.put("error", 1);
			resultMap.put("message", "上传失败");
			return resultMap;
		} 
		finally {
		}
		
	}

}
