package Zadanie3;

import Zadanie3.dao.UserDao;
import Zadanie3.dao.UserDaoJpaImpl;
import Zadanie3.model.User;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.math.BigDecimal;

public class Execute {

    private UserDao dao;

    public void setup() {
        dao = new UserDaoJpaImpl();
    }

    public void Main() {
        setup();
    }

}
