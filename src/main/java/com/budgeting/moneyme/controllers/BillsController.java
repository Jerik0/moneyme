package com.budgeting.moneyme.controllers;

import com.budgeting.moneyme.models.Bill;
import com.budgeting.moneyme.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Controller
public class BillsController {

    private final BillRepository billsDao;

    @Autowired
    public BillsController(BillRepository billsDao) {
      this.billsDao = billsDao;
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

      if(month == 12) { //There is no 13th month, therefore we start over at 1.
        nextMonth = 1;
      } else {
        nextMonth = month + 1;
      }

      if(month >= 10) { //date is further up or further down the string depending on a 1 or 2 digit month.
        date = Integer.parseInt(timestamp.substring(3,5));
      } else {
        date = Integer.parseInt(timestamp.substring(2,4));
      }

      System.out.println("date: " + date);

      List<Bill> currentBills = billsDao.findCurrentBills(date);

      model.addAttribute("timestamp", timestamp);
      model.addAttribute("nextMonth", nextMonth);
      model.addAttribute("month", month);
      model.addAttribute("date", date);
      model.addAttribute("year", year);
      model.addAttribute("bills", billsDao.findAll());
      model.addAttribute("currentBills", currentBills);
      return "/index";
    }
}
