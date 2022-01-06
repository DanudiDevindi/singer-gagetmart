package com.bolton.singer_gagetmart.api.repo;

import java.util.List;

import com.bolton.singer_gagetmart.api.dto.ItemDTO;


public interface ItemRepo {
	
	 List<ItemDTO> getAllItems() throws Exception;

}
