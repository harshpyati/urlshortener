package com.harsh.urlshortener.repo;

import com.harsh.urlshortener.domain.UrlStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UrlStatisticsRepository extends JpaRepository<UrlStatistics, String> {
    @Query(value = "select url, count from url_statistics order by count desc limit 10", nativeQuery = true)
    List<UrlStatistics> getMostUsedUrls();
}
