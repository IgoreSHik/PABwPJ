package PABwPJZadanie1.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountDao<Long, BigDecimal> {
    void save(Long name);
    void delete (Long name);
    void update (Long name);
    Optional<Long> findById(K id);
    List<Long> findAll();
}