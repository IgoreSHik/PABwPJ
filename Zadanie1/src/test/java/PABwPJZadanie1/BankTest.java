package PABwPJZadanie1;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BankTest {

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

    @Test
    public void test3() {
        Bank a = new BankImpl();
        Long idA, idB;
        idA = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        idB = a.createAccount("Balyka Ihar", "Zwierzyniecka");
        assert idA.compareTo(idB) == 0;
    }

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