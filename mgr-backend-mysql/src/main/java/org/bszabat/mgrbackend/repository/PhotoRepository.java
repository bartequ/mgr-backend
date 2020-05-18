package org.bszabat.mgrbackend.repository;

import org.bszabat.mgrbackend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query(value = "SELECT max(id) FROM photos", nativeQuery = true)
    Optional<Long> findMaxId();

    @Query(value = "SELECT min(id) FROM photos", nativeQuery = true)
    Optional<Long> findMinId();
}
