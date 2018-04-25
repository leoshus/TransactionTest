package com.sdw.soft.service;

import com.sdw.soft.dao.UserDao;
import com.sdw.soft.meta.Address;
import com.sdw.soft.meta.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by shangyd on 2018/4/22.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public List<User> getAll() {
        return userDao.getAll();
    }


    public int noAnnotationMethod(User user) {
        int count = addUser(user);
        return count;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int addUser(User user) {
        String message = null;
        int count = userDao.addUser(user);
        message.length();
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insert(User user) {
        String insert = null;
        int count = this.addUser(user);
        User user1 = new User();
        user1.setUserName("Leo");
        user1.setAge(27);
        userDao.addUser(user1);
//        insert.length();
        return count;
    }

    public int manualInsert(User user) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            int count = userDao.addUser(user);
            String manual = null;
//            manual.length();
            transactionManager.commit(transactionStatus);
            return count;
        } catch (TransactionException e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
        }
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void manualAndAnnotation(User user) {
        userDao.addUser(user);
        String annotation = null;
        annotation.length();
        user.setUserName("Annotation");
        manualInsert(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequired(User user) {
        userDao.addUser(user);
        user.setUserName("requires_new");

        insertRequiresNew(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(User user) {
        String requiresNew = null;
        userDao.addUser(user);
        requiresNew.length();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequiredAndRequiresNewWithManual(User user) {
        userDao.addUser(user);
        user.setUserName("RequiresNew");
        insertRequiresNewWithManual(user);
    }

    public void insertRequiresNewWithManual(User user) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = transactionManager.getTransaction(definition);
        try {
            String requiresNew = null;
            userDao.addUser(user);
            requiresNew.length();
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
        }
    }

    @Transactional
    public void addAndAsync(User user, Address address) {
        userDao.addUser(user);
        addressService.addAddress(address);
    }
}
