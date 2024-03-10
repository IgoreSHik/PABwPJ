package PABwPJZadanie1;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankImpl implements Bank {

    public static void main(String[] args) {
        System.out.println("start");
    }

        private Map<Long, BigDecimal> data = new HashMap<Long, BigDecimal>();

        @Override
        public Long createAccount(String name, String address) {
            Long key = Long.valueOf((name+address).hashCode());
            if (!data.containsKey(key)) data.put(key, BigDecimal.valueOf(0));
            return key;
        }

        @Override
        public Long findAccount(String name, String address) {
            Long key = Long.valueOf((name+address).hashCode());
            if (!data.containsKey(key)) return null;
            return key;
        }

        @Override
        public void deposit(Long id, BigDecimal amount) {
            if (data.containsKey(id)) data.put(id, data.get(id).add(amount));
            else new AccountIdException();
        }

        @Override
        public BigDecimal getBalance(Long id) {
            if (data.containsKey(id)) return data.get(id);
            else new AccountIdException();
            return null;
        }

        @Override
        public void withdraw(Long id, BigDecimal amount) {
            if (!data.containsKey(id)) { new AccountIdException(); return; }
            if (data.get(id).compareTo(amount) == -1) { new InsufficientFundsException(); return; }
            data.put(id, data.get(id).subtract(amount));
        }

        @Override
        public void transfer(Long idSource, Long idDestination, BigDecimal amount) {
            withdraw(idSource, amount);
            deposit(idDestination, amount);
        }
    }