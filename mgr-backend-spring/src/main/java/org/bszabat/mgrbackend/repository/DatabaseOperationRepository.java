package org.bszabat.mgrbackend.repository;

import org.bszabat.mgrbackend.model.DatabaseOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseOperationRepository extends JpaRepository<DatabaseOperation, Long> {
}
