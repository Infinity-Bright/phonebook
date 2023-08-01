package com.ashit.repository;

import com.ashit.request.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PhoneBookRepository extends JpaRepository<Subscriber, Long> {
    @Query(
            value = "SELECT * FROM SUBSCRIBER where name = ?1",
            nativeQuery = true)
    public Subscriber findByName(String name);

    @Modifying
    @Query(
            value = "DELETE FROM SUBSCRIBER where name = ?1",
            nativeQuery = true)
    public Integer deleteByName(String name);

}
