package com.budgeting.moneyme.models;

import javax.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @Column()
  private int amount;

  @Column()
  private int date;

  @Column()
  private boolean income;

  public Bill() {

  }

  public Bill(boolean income) {

    this.income = income;
  }

  public Bill(Bill copy, boolean income) {
    id = copy.id;
    amount = copy.amount;
    date = copy.date;
    name = copy.name;
    this.income = income;
  }

  public Bill(String name, int amount, int date, boolean income) {
    this.name = name;
    this.amount = amount;
    this.date = date;
    this.income = income;
  }

  public Bill(Long id, String name, int amount, int date, boolean income) {
    this.name = name;
    this.amount = amount;
    this.date = date;
    this.id = id;
    this.income = income;
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
