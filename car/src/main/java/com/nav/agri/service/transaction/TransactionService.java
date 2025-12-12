package com.nav.agri.service.transaction;

import com.nav.agri.dto.transaction.TransactionCreateDTO;
import com.nav.agri.dto.transaction.TransactionDTO;
import java.util.List;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionCreateDTO dto);
    TransactionDTO getTransaction(int id);
    List<TransactionDTO> getAllTransactions();
    TransactionDTO updateTransaction(int id, TransactionCreateDTO dto);
    void deleteTransaction(int id);
}
