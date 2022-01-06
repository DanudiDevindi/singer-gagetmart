package com.bolton.singer_gagetmart.api.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;

import com.bolton.singer_gagetmart.api.db.DBConnection;
import com.bolton.singer_gagetmart.api.dto.ItemDTO;
import com.bolton.singer_gagetmart.api.repo.ItemRepo;


@Repository
public class ItemRepoImpl implements ItemRepo {

	private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "select i.item_id,i.name,i.description,i.image,i.price,i.deliveryCost,b.brand_name,c.category_name,i.discount,i.shop,i.warranty,i.soldOut from item i,brand b,category c where i.brand=b.brand_id && i.category=c.category_id";

        preparedStatement = connection.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();
        List<ItemDTO> items = new ArrayList<>();
        while (resultSet.next()) {
            ItemDTO item = new ItemDTO();
            item.setId(resultSet.getString(1));
            item.setName(resultSet.getString(2));
            item.setDescription(resultSet.getString(3));
            item.setImage(resultSet.getString(4));
            item.setPrice(resultSet.getDouble(5));
            item.setDeliveryCost(resultSet.getDouble(6));
            item.setBrand(resultSet.getString(7));
            item.setCategory(resultSet.getString(8));
            item.setDiscount(resultSet.getInt(9));
            item.setShop(resultSet.getString(10));
            item.setWarranty(resultSet.getString(11));
            item.setSoldOut(resultSet.getBoolean(12));
            items.add(item);
        }
        closeConnection();
        return items;
    }

    private void closeConnection() {
        try {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.close(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	
	
	

}
