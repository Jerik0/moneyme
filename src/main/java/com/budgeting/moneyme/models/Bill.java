package com.budgeting.moneyme.models;
import javax.persistence.*;

@Entity
@Table(name="bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column()
    private int amount;

    @Column()
    private int date;

    public Bill() {

    }

    public Bill(Bill copy) {
        id = copy.id;
        amount = copy.amount;
        date = copy.date;
        name = copy.name;
    }

    public Bill(String name, int amount, int date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public Bill(Long id, String name, int amount, int date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getDate() {
        return date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
