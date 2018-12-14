package cn.liaozhi.service;

import java.util.List;

import cn.liaozhi.pojo.Items;

public interface ItemService {

	
	public List<Items> list() throws Exception;
	
	public Items findItemsById(Integer id) throws Exception;
	
	public void updateItems(Items items) throws Exception;
	
	public String insertItems(Items items) throws Exception;
	
}
