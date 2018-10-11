package com.budgeting.moneyme.controllers;

import com.budgeting.moneyme.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BillsController {

    private final BillRepository billsDao;

    @Autowired
    public BillsController(BillRepository billsDao) {
        this.billsDao = billsDao;
    }
}
