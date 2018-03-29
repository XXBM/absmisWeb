package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.SubUnitEnIndustrialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubUnitEnIndustrializationRepository extends MyRepository<SubUnitEnIndustrialization, Long> {
    SubUnitEnIndustrialization getBySubUnitEnIdAndYearAndQuarter(Long id, Integer year, Integer quarter);
    List<SubUnitEnIndustrialization> findBySubmit(Boolean submit);
    List<SubUnitEnIndustrialization> findBySubUnitEnId(Long subUnitEnId);
    Page<SubUnitEnIndustrialization> findBySubmit(Boolean submit, Pageable pageable);
    Page<SubUnitEnIndustrialization> findBySubUnitEnId(Long subUnitEnId, Pageable pageable);
}
