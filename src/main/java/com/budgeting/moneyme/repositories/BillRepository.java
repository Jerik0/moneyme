package com.budgeting.moneyme.repositories;

import com.budgeting.moneyme.models.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {
  Bill findByName(String name);

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM bills b WHERE b.date >= ?1 ORDER BY date"
  )
  List<Bill> findCurrentBills(int date);

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM bills b WHERE b.id != ?1 ORDER BY date"
  )
  List<Bill> findBillsExcept(Long id);

  @Query(nativeQuery = true,
    value = "INSERT INTO bills (amount, date, name, income) VALUES (1474, ?1, 'Emma Check', true)"
  )
  void insertBill(int date);
}
