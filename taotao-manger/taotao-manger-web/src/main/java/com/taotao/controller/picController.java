package com.taotao.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.until.JsonUtils;
import com.taotao.service.pictureService;

/**
 *@title: picController.java
 * @package com.taotao.controller
 * @description: TODO
 * @copyright: 技术部 (c) 
 * @author storm
 * @date 2017年4月26日
 * @version 1.0
 */
@Controller
public class picController {
	@Autowired
	private pictureService pictureService;
	@RequestMapping("/pic/upload")
	@ResponseBody
public String pictureupload(MultipartFile uploadFile){
	Map result = pictureService.uploadPicture(uploadFile);
	String json = JsonUtils.objectToJson(result);
	System.out.println(result);
	return json;
}
}
