package com.dabai.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dabai.springmvc.dao.ItemsMapper;
import com.dabai.springmvc.pojo.Items;
@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsMapper itemMapper;
	
	@Override
	public List<Items> selectItemList() {
		itemMapper.selectByExampleWithBLOBs(null);
		return null;
	}

	@Override
	public Items selectItemsById(Integer id) {
		Items items = itemMapper.selectByPrimaryKey(id);
		return items;
	}

	@Override
	public void updateItemsById(Items items) {
		itemMapper.updateByPrimaryKeyWithBLOBs(items);
		
	}

}
