package Zadanie3;

import Zadanie3.dao.UserDao;
import Zadanie3.dao.UserDaoJpaImpl;
import Zadanie3.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BankTest {

    private UserDao dao;

    @BeforeEach
    public void setup() {
        dao = new UserDaoJpaImpl();
    }

    @AfterEach
    public void clear() {
        //  usuwa uzytkownikow po kazdym tescie
        for (User u:dao.findAll())
            dao.delete(u);
    }

    @Test
    public void test1() {
        Bank a = new BankImpl();
        a.createAccount("Balyka Ihar", "Zwierzyniecka");
        assert a.findAccount("Balyka Ihar", "Zwierzyniecka") != null;
    }

    @Test
    public void test2() {
        Bank a = new BankImpl();
        Long idA;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        a.deposit(idA, BigDecimal.valueOf(100));
        assert a.getBalance(idA).compareTo(BigDecimal.valueOf(100)) == 0;
    }

    /*@Test
    public void test3() {
        Bank a = new BankImpl();
        Long idA, idB;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        idB = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        assert idA.compareTo(idB) == 0;
    }*/

    @Test
    public void test4() {
        Bank a = new BankImpl();
        Long idA, idB;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        idB = a.createAccount("Drugi uzytkownik", "Gdziestam");
        assert idA.compareTo(idB) != 0;
    }

    @Test
    public void test5() {
        Bank a = new BankImpl();
        Long idA, idB;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        a.deposit(idA, BigDecimal.valueOf(100));
        idB = a.createAccount("Drugi uzytkownik", "Gdziestam");
        a.transfer(idA, idB, BigDecimal.valueOf(100));
        assert a.getBalance(idB).compareTo(BigDecimal.valueOf(100)) == 0;
    }

    @Test
    public void test6() {
        Bank a = new BankImpl();
        Long idA, idB;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        a.deposit(idA, BigDecimal.valueOf(100));
        idB = a.createAccount("Drugi uzytkownik", "Gdziestam");
        a.transfer(idA, idB, BigDecimal.valueOf(100));
        assert a.getBalance(idA).compareTo(BigDecimal.valueOf(0)) == 0;
    }

    @Test
    public void test7() {
        Bank a = new BankImpl();
        Long idA;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        a.deposit(idA, BigDecimal.valueOf(100));
        a.withdraw(idA,BigDecimal.valueOf(33));
        assert a.getBalance(idA).compareTo(BigDecimal.valueOf(67)) == 0;
    }
}