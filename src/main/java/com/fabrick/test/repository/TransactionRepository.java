package com.fabrick.test.repository;


import com.fabrick.test.entity.TransactionEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Configuration
@ComponentScan
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
}
