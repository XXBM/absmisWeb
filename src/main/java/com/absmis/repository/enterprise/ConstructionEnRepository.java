package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ConstructionEn;
import org.springframework.stereotype.Repository;


@Repository
public interface ConstructionEnRepository extends MyRepository<ConstructionEn, Long> {
}
