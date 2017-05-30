package com.taotao.service;

import java.util.List;

import com.taotao.common.EUTreeNode;
import com.taotao.pojo.TbItemCat;

public interface ItemCatService {
List<EUTreeNode> getCatList(long parentId);
}
