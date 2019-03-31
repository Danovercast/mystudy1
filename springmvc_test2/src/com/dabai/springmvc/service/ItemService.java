package com.dabai.springmvc.service;

import java.util.List;

import com.dabai.springmvc.pojo.Items;

public interface ItemService {

	public List<Items> selectItemList();

	public Items selectItemsById(Integer id);

	public void updateItemsById(Items items);

}
