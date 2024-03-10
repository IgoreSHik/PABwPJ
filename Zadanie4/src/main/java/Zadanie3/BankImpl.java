package Zadanie3;

import Zadanie3.dao.UserDao;
import Zadanie3.dao.UserDaoJpaImpl;
import Zadanie3.model.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BankImpl implements Bank {

    private final static Logger log = Logger.getLogger(BankImpl.class.getName());

    public BankImpl()
    {
        log.info("Utworzenie instancji banku");
        try {
            main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("start");
        LogManager.getLogManager().readConfiguration(Bank.class.getResourceAsStream("/logging.properties"));
    }

    //private Map<Long, BigDecimal> data = new HashMap<Long, BigDecimal>();

    UserDao dao = new UserDaoJpaImpl();

        @Override
        public Long createAccount(String name, String address) {
            log.fine("Rozpoczęcie  createAccount");
            Long key = Long.valueOf((name+address).hashCode());

            //if (!data.containsKey(key)) data.put(key, BigDecimal.valueOf(0));

            try {
                User u = new User(key, name, address, BigDecimal.valueOf(0));
                dao.save(u);

                log.fine("Zakończenie createAccount");
                return u.getId();
            } catch (Exception e) {
                e.printStackTrace();
                return key;
            }

            //return key;
        }

        @Override
        public Long findAccount(String name, String address) {
            log.fine("Rozpoczęcie findAccount");
            Long key = Long.valueOf((name+address).hashCode());

            //if (!data.containsKey(key)) { log.fine("Zakończenie findAccount"); return null; }

            if (dao.findById(key) == null) { log.fine("Zakończenie findAccount"); return null; };

            log.fine("Zakończenie findAccount");
            return dao.findById(key);
        }

        @Override
        public void deposit(Long id, BigDecimal amount) {
            log.fine("Rozpoczęcie deposit");

            /*if (data.containsKey(id)) data.put(id, data.get(id).add(amount));
            else new AccountIdException();*/

            if (dao.findById(id) != null) {
                User u = dao.findById(id).get();
                u.setMoney(u.getMoney().add(amount));
                dao.update(u);
            } else new AccountIdException();

            log.fine("Zakończenie deposit");
        }

        @Override
        public BigDecimal getBalance(Long id) {
            log.fine("Rozpoczęcie getBalance");

            //if (data.containsKey(id)) { log.fine("Zakończenie getBalance"); return data.get(id); }
            //else { log.severe("getBalance AccountIdException"); new AccountIdException(); }

            if (dao.findById(id) != null) {
                User u = dao.findById(id).get();
                return u.getMoney();
            } else new AccountIdException();

            return null;
        }

        @Override
        public void withdraw(Long id, BigDecimal amount) {
            log.fine("Rozpoczęcie withdraw");

            //if (!data.containsKey(id)) { log.severe("withdraw AccountIdException"); new AccountIdException(); return; }
            //if (data.get(id).compareTo(amount) == -1) { log.severe("withdraw InsufficientFundsException"); new InsufficientFundsException(); return; }
            //data.put(id, data.get(id).subtract(amount));

            if (dao.findById(id) == null)  { log.severe("withdraw AccountIdException"); new AccountIdException(); return; }
            User u = dao.findById(id).get();
            if (u.getMoney().compareTo(amount) == -1) { log.severe("withdraw InsufficientFundsException"); new InsufficientFundsException(); return; }
            u.setMoney(u.getMoney().subtract(amount));
            dao.update(u);

            log.fine("Zakończenie withdraw");
        }

        @Override
        public void transfer(Long idSource, Long idDestination, BigDecimal amount) {
            log.fine("Rozpoczęcie transfer");
            withdraw(idSource, amount);
            deposit(idDestination, amount);
            log.fine("Zakończenie transfer");
        }
    }