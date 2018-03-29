package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ConstructionEnIndustrialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConstructionEnIndustrializationRepository extends MyRepository<ConstructionEnIndustrialization, Long> {
    ConstructionEnIndustrialization getByConstructionEnIdAndYearAndQuarter(Long id, Integer year, Integer quarter);
    List<ConstructionEnIndustrialization> findBySubmit(Boolean submit);
    List<ConstructionEnIndustrialization> findByConstructionEnId(Long constructionEnId);
    Page<ConstructionEnIndustrialization> findBySubmit(Boolean submit, Pageable pageable);
    Page<ConstructionEnIndustrialization> findByConstructionEnId(Long constructionEnId, Pageable pageable);
}
