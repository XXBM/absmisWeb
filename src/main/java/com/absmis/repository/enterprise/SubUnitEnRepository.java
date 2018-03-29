package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.SubUnitEn;
import org.springframework.stereotype.Repository;


@Repository
public interface SubUnitEnRepository extends MyRepository<SubUnitEn, Long> {
}
