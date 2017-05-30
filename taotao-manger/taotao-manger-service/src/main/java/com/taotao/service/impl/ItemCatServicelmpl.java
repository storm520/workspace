package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServicelmpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		//创建查询条件;
		TbItemCatExample example = new TbItemCatExample();
		//设置查询语句
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			//创建一个TreeNode的对象
			 EUTreeNode treeNode= new EUTreeNode();
			 treeNode.setId(tbItemCat.getId());
			 treeNode.setText(tbItemCat.getName());
			 treeNode.setStaute(tbItemCat.getIsParent()?"closed":"open");
			 
			resultList.add(treeNode);
			//System.out.println(tbItemCat.getIsParent()?"closed":"open");
		}
		return resultList;
	}

}
