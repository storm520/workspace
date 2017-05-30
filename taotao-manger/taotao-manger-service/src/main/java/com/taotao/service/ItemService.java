package com.taotao.service;

import com.taotao.common.EasyUIResult;
import com.taotao.common.until.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIResult getItemList(int page,int rows);
	TaotaoResult createItem(TbItem item,String desc) throws Exception;
}
