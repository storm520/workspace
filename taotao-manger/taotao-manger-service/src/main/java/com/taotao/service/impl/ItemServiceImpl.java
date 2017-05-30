package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EasyUIResult;
import com.taotao.common.until.IDUtils;
import com.taotao.common.until.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
/**
 *@title: ItemServiceImpl.java
 * @package com.taotao.service.impl
 * @description: TODO
 * @copyright: 技术部 (c) 
 * @author storm
 * @date 2017年5月14日
 * @version 1.0
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem getItemById(long itemId) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	@Override
	public EasyUIResult getItemList(int page, int rows) {
		TbItemExample example=new TbItemExample();
		//分页管理
		PageHelper.startPage(page, rows);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		
		return result;
	}
	
	@Override
	public TaotaoResult createItem(TbItem item,String desc) throws Exception {
		//生成商品ID
		Long Id = IDUtils.genItemId();
		
		//补全不完整的字段
		item.setId(Id);
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		
		//添加到数据库中
		itemMapper.insert(item);
		
		//添加商品信息
		TaotaoResult result=insertdec(Id,desc);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	//输入的文件名要和item-add的商品描述中的name一致，否则desc中的值无法写入
	private TaotaoResult insertdec(Long itemId,String desc){
		TbItemDesc itemdec =new TbItemDesc();
		itemdec.setItemId(itemId);
		itemdec.setCreated(new Date());
		itemdec.setUpdated(new Date());
		itemdec.setItemDesc(desc);
		itemDescMapper.insert(itemdec);
		return TaotaoResult.ok();
	}
		
}
