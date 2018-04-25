package com.sdw.soft.service;

import com.sdw.soft.dao.AddressDao;
import com.sdw.soft.meta.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by shangyindong on 2018/4/25.
 */
@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    @Async
    public int addAddress(Address address) {
        int i = addressDao.addAddress(address);
        String count = null;
        count.length();
        return i;
    }
}
