package Zadanie3.dao;

import java.util.List;

public interface GenericDao<T,K>  {
    void save(T t);
    void delete (T t);
    void update (T t);
    Long findById(K id);
    List<T> findAll();
}
