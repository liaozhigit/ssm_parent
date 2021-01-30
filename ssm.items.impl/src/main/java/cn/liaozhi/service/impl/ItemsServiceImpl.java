package cn.liaozhi.service.impl;

import java.util.List;

import cn.liaozhi.core.util.JsonUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.liaozhi.dao.ItemsMapper;
import cn.liaozhi.pojo.Items;
import cn.liaozhi.pojo.ItemsExample;
import cn.liaozhi.service.ItemService;

@Service
public class ItemsServiceImpl implements ItemService {
	private Logger log = Logger.getLogger(ItemsServiceImpl.class);
	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public List<Items> list() throws Exception {
		//如果不需要任何查询条件,直接将example对象new出来即可
		ItemsExample example = new ItemsExample();
		List<Items> list = itemsMapper.selectByExampleWithBLOBs(example);
	//	log.info("fastjson============"+JSON.toJSONString(list));
	//	log.info(JsonUtil.objectToJson(list));
		return list;
	}

	@Override
	public Items findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		return items;
	}

	@Override
	public void updateItems(Items items) throws Exception {
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}

	@Override
	public String insertItems(Items items) throws Exception {
		itemsMapper.insert(items);
		return String.valueOf(items.getId());
		
	}
	
	
}
