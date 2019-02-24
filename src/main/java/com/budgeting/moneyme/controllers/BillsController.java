package com.budgeting.moneyme.controllers;

import com.budgeting.moneyme.models.Bill;
import com.budgeting.moneyme.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.time.DayOfWeek;

@Controller
public class BillsController {

  private final BillRepository billsDao;

  @Autowired
  public BillsController(BillRepository billsDao) {
    this.billsDao   = billsDao;
  }

  @GetMapping("/bills")
  public String bills(Model model) {
    Instant instant = Instant.now();
    DateTimeFormatter formatter =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.US)
            .withZone(ZoneId.systemDefault());
    String timestamp = formatter.format(instant);
    int month = Integer.parseInt(timestamp.substring(0,2)); //retrieve current month
    int year = Integer.parseInt(timestamp.substring(6,8));
    int date;
    int nextMonth;
    int lastDayOfMonth;
    String currentDate;

    if(month == 12) { //There is no 13th month, so we start over at 1.
      nextMonth = 1;
    } else {
      nextMonth = month + 1;
    }

    int thirdMonth = nextMonth + 1;

    System.out.println(timestamp);

    if(month >= 10) {
      currentDate = timestamp.substring(0,8);
    } else {
      currentDate = timestamp.substring(0,7);
    }

//    LocalDate dateToLookAt = LocalDate.of(Integer.parseInt("20" + timestamp.substring(6,8)), );

    //find last date of the month
    LocalDate localDate = LocalDate.parse(currentDate,DateTimeFormatter.ofPattern("MM/dd/yy"));
    String completeDateString = localDate.with(TemporalAdjusters.lastDayOfMonth()).toString();

    if(month >= 10) { //date is further up or further down the string depending on a 1 or 2 digit month.
      date = Integer.parseInt(timestamp.substring(3,5));
      lastDayOfMonth = Integer.parseInt(completeDateString.substring(8));
    } else {
      date = Integer.parseInt(timestamp.substring(2,4));
      lastDayOfMonth = Integer.parseInt(completeDateString.substring(7));
    }
//    billsDao.insertBill(4);

    System.out.println(lastDayOfMonth);

    List<Bill> currentBills = billsDao.findCurrentBills(date);

    model.addAttribute("timestamp", timestamp);
    model.addAttribute("thirdMonth", thirdMonth);
    model.addAttribute("nextMonth", nextMonth);
    model.addAttribute("month", month);
    model.addAttribute("date", date);
    model.addAttribute("year", year);
    model.addAttribute("bills", billsDao.findAll());
    model.addAttribute("currentBills", currentBills);
    return "/index";
  }

  @GetMapping("/{id}/update")
  public String update(@PathVariable Long id, Model model) {
    Bill bill = billsDao.findOne(id);
    model.addAttribute("allButOne", billsDao.findBillsExcept(id));
    model.addAttribute("bill", bill);
    return "/update";
  }

  @PostMapping("/{id}/update/confirm")
  public String confirmUpdate(@PathVariable Long id, @ModelAttribute Bill editedBill) {
    Bill oldBill = billsDao.findOne(id);
    oldBill.setDate(editedBill.getDate());
    oldBill.setAmount(editedBill.getAmount());
    oldBill.setDate(editedBill.getDate());
    billsDao.save(oldBill);
    return "redirect:/bills";
  }

  @PostMapping("/{id}/delete")
  public String deleteBill(@PathVariable Long id) {
    billsDao.delete(id);
    return "redirect:/bills";
  }
}
