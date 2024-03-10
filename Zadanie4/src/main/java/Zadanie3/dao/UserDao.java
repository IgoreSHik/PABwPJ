package Zadanie3.dao;

import Zadanie3.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao extends GenericDao<User,Long> {
    List<User> findByNameAndAdress(String name, String address);
    List<User> findByNameFragment(String name);
    public List<User> findByMoney(BigDecimal x, BigDecimal y);
    public List<User> findRichest();
}