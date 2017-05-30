package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class testPagecontroller {
@Test
public void testPageHelper(){
	//创建一个spring容器
	ApplicationContext applicationContext =new 
			ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	//从spring容器中获得mapper代理对象
	TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
	
	//执行查询，并分页
	TbItemExample example =new TbItemExample();
	//分页处理
	PageHelper.startPage(1, 10);
	List<TbItem> list = mapper.selectByExample(example);
	//取出商品列表
	for (TbItem tbItem : list) {
		System.out.println(tbItem.getTitle());
	}
	//取出分页总数
	PageInfo<TbItem> pageinfo =new PageInfo<>(list);
	long total = pageinfo.getTotal();
	System.out.println("信息总共有:"+total);
	
	
}
}
