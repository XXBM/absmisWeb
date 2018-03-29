package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.MachineryEnIndustrialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MachineryEnIndustrializationRepository extends MyRepository<MachineryEnIndustrialization, Long> {
    MachineryEnIndustrialization getByMachineryEnIdAndYearAndQuarter(Long id,Integer year,Integer quarter);
    MachineryEnIndustrialization getByYearAndQuarter(Integer year,Integer quarter);
    List<MachineryEnIndustrialization> findBySubmit(Boolean submit);
    List<MachineryEnIndustrialization> findByMachineryEnId(Long machineryEnId);
    Page<MachineryEnIndustrialization> findBySubmit(Boolean submit, Pageable pageable);
    Page<MachineryEnIndustrialization> findByMachineryEnId(Long machineryEnId, Pageable pageable);
}
