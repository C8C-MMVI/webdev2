package com.nav.agri.service.transactiondetails;

import com.nav.agri.dto.transactiondetails.TransactionDetailsCreateDTO;
import com.nav.agri.dto.transactiondetails.TransactionDetailsDTO;
import java.util.List;

public interface TransactionDetailsService {

    TransactionDetailsDTO createTransactionDetails(TransactionDetailsCreateDTO dto);

    TransactionDetailsDTO getTransactionDetails(int id);

    List<TransactionDetailsDTO> getAllTransactionDetails();

    TransactionDetailsDTO updateTransactionDetails(int id, TransactionDetailsCreateDTO dto);

    void deleteTransactionDetails(int id);
}
