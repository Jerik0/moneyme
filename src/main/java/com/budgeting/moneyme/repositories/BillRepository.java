package com.budgeting.moneyme.repositories;

import com.budgeting.moneyme.models.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Bill findByName(String name);
}
