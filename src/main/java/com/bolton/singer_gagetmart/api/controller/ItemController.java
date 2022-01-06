
package com.bolton.singer_gagetmart.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolton.singer_gagetmart.api.dto.Items;
import com.bolton.singer_gagetmart.api.repo.ItemRepo;


@Controller
@CrossOrigin
public class ItemController {
	
	@Autowired
    private ItemRepo itemRepo;

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllItems() {
        try {
            Items items = new Items();
            items.setItems(itemRepo.getAllItems());

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
