package Zadanie3.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="\"USER\"") // konfilkt ze slowem kluczowym SQL w H2 w wersjach 2.x., wymagane escapowanie
public class User extends AbstractModel {

    @Column(unique = true)
    private String name;
    private String address;
    private BigDecimal money;

    public User() {
    }

    public User(Long id, String name, String address, BigDecimal money) {
        this.name = name;
        this.address = address;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", money='" + money.toString() + '\'' +
                '}';
    }

}
