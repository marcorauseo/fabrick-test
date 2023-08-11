//import com.fabrick.test.entity.TransactionEntity;
//import com.fabrick.test.model.AccountTransactionsResponseModel;
//import com.fabrick.test.model.base.Transaction;
//import com.fabrick.test.repository.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Consumer;
//
//@Component
//public class GetAccountTransactionCommand {
//
//
//
//    public void saveTransactions(AccountTransactionsResponseModel response) {
//        List<Transaction> transactions = Optional.ofNullable(response.getPayload())
//                .map(AccountTransactionsResponseModel.Payload::getList)
//                .orElse(Collections.emptyList());
//
//        transactions.forEach(transaction -> {
//            TransactionEntity dbEntity = new TransactionEntity();
//
//            Optional.ofNullable(transaction.getTransactionId()).ifPresent(assignTo(dbEntity::setTransactionId));
//            Optional.ofNullable(transaction.getOperationId()).ifPresent(assignTo(dbEntity::setOperationId));
//            Optional.ofNullable(transaction.getAccountingDate()).ifPresent(assignTo(dbEntity::setAccountingDate));
//            Optional.ofNullable(transaction.getValueDate()).ifPresent(assignTo(dbEntity::setValueDate));
//
//            Transaction.TransactionType transactionType = transaction.getTransactionType();
//            if (transactionType != null) {
//                TransactionEntity.TransactionType dbTransactionType = new TransactionEntity.TransactionType();
//                Optional.ofNullable(transactionType.getEnumeration()).ifPresent(assignTo(dbTransactionType::setEnumeration));
//                Optional.ofNullable(transactionType.getValue()).ifPresent(assignTo(dbTransactionType::setValue));
//                dbEntity.setTransactionType(dbTransactionType);
//            }
//
//            Optional.ofNullable(transaction.getAmount()).ifPresent(assignTo(dbEntity::setAmount));
//            Optional.ofNullable(transaction.getCurrency()).ifPresent(assignTo(dbEntity::setCurrency));
//            Optional.ofNullable(transaction.getDescription()).ifPresent(assignTo(dbEntity::setDescription));
//
//            transactionRepository.save(dbEntity);
//        });
//    }
//
//    private <T> Consumer<T> assignTo(Consumer<T> setter) {
//        return value -> setter.accept(value);
//    }
//}
