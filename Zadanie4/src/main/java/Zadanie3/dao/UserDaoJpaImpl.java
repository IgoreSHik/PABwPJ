package Zadanie3.dao;

import Zadanie3.model.User;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class UserDaoJpaImpl extends GenericDaoJpaImpl<User,Long> implements  UserDao {
    EntityManager em = getEntityManager();
    @Override
    public List<User> findByNameAndAdress(String name, String address) {
        Query query = em.createQuery("select x from User x where x.name='" + name + "' and x.address='" + address + "';");
        return query.getResultList();
    }

    @Override
    public List<User> findByNameFragment(String name) {
        Query query = em.createQuery("select x from User x where x.name like " + name + "%;");
        return query.getResultList();
    }

    @Override
    public List<User> findByMoney(BigDecimal x, BigDecimal y) {
        Query query = em.createQuery("select x from User x where x.money between " + x.toString() + " and " + y.toString() + ";");
        return query.getResultList();
    }

    @Override
    public List<User> findRichest() {
        Query query = em.createQuery("select x from User x where x.money = max(x.money);");
        return query.getResultList();
    }
}