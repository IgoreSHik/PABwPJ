package PABwPJZadanie1;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
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

    private Map<Long, BigDecimal> data = new HashMap<Long, BigDecimal>();

        @Override
        public Long createAccount(String name, String address) {
            log.fine("Rozpoczęcie  createAccount");
            Long key = Long.valueOf((name+address).hashCode());
            if (!data.containsKey(key)) data.put(key, BigDecimal.valueOf(0));
            log.fine("Zakończenie createAccount");
            return key;
        }

        @Override
        public Long findAccount(String name, String address) {
            log.fine("Rozpoczęcie findAccount");
            Long key = Long.valueOf((name+address).hashCode());
            if (!data.containsKey(key)) { log.fine("Zakończenie findAccount"); return null; }
            log.fine("Zakończenie findAccount");
            return key;
        }

        @Override
        public void deposit(Long id, BigDecimal amount) {
            log.fine("Rozpoczęcie deposit");
            if (data.containsKey(id)) data.put(id, data.get(id).add(amount));
            else new AccountIdException();
            log.fine("Zakończenie deposit");
        }

        @Override
        public BigDecimal getBalance(Long id) {
            log.fine("Rozpoczęcie getBalance");
            if (data.containsKey(id)) { log.fine("Zakończenie getBalance"); return data.get(id); }
            else { log.severe("getBalance AccountIdException"); new AccountIdException(); }
            return null;
        }

        @Override
        public void withdraw(Long id, BigDecimal amount) {
            log.fine("Rozpoczęcie withdraw");
            if (!data.containsKey(id)) { log.severe("withdraw AccountIdException"); new AccountIdException(); return; }
            if (data.get(id).compareTo(amount) == -1) { log.severe("withdraw InsufficientFundsException"); new InsufficientFundsException(); return; }
            data.put(id, data.get(id).subtract(amount));
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