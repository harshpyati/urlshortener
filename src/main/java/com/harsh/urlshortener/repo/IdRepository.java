package com.harsh.urlshortener.repo;

import com.harsh.urlshortener.domain.URLIdRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdRepository extends JpaRepository<URLIdRange, Integer> {
    @Query(value = "select id from url_id_range where current != end_range", nativeQuery = true)
    List<Integer> getAllIds();
}
