package com.nav.agri.repositories;

import com.nav.agri.models.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
}
