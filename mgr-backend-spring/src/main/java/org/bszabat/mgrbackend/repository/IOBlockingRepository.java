package org.bszabat.mgrbackend.repository;

import org.bszabat.mgrbackend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOBlockingRepository extends JpaRepository<Photo, Integer> {
}
