package org.example.repository;

import org.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Account, String> {
  Account   findByExternalId(String externalId);
  List<Account> findByStateId(Integer id);
}