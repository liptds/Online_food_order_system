package com.laioffer.onlineOrder.service;

import com.laioffer.onlineOrder.dao.MenuInfoDao;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.laioffer.onlineOrder.entity.MenuItem;

@Service
public class MenuInfoService {

    @Autowired
    private MenuInfoDao menuInfoDao;


    public List<Restaurant> getRestaurants() {
        return menuInfoDao.getRestaurants();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        return menuInfoDao.getAllMenuItem(restaurantId);
    }

    public MenuItem getMenuItem(int id) {
        return menuInfoDao.getMenuItem(id);
    }
}


