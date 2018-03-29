package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.UnitEngineering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitEngineeringRepository extends MyRepository<UnitEngineering, Long> {
    List<UnitEngineering> findByStructureFormId(Long id);
    List<UnitEngineering> findByProjectId(Long id);
    Page<UnitEngineering> findByProjectId(Long id, Pageable pageable);
}
