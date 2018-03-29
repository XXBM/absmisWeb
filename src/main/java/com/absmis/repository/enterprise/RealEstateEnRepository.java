package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.RealEstateEn;
import org.springframework.stereotype.Repository;


@Repository
public interface RealEstateEnRepository extends MyRepository<RealEstateEn, Long> {
}
