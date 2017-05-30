package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUIResult;
import com.taotao.common.until.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
@Autowired
private ItemService itemService;
@RequestMapping("/item/{/itemId}")
@ResponseBody
public TbItem getItemById(@PathVariable Long itemId){
	TbItem tbItem = itemService.getItemById(itemId);
	return tbItem;
}
//商品列表展示
@RequestMapping("/item/list")	
@ResponseBody
public EasyUIResult getItemList(Integer page,Integer rows){
	EasyUIResult itemList = itemService.getItemList(page, rows);
	return itemList;
}
//添加商品信息
@RequestMapping(value="/item/save",method=RequestMethod.POST)
@ResponseBody
public TaotaoResult createItem(TbItem item,String desc) throws Exception{
	TaotaoResult result = itemService.createItem(item,desc);
	return result;
}
}
