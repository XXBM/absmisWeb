package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.RealEstateEnQualification;
import org.springframework.stereotype.Repository;




@Repository
public interface RealEstateEnQualificationRepository extends MyRepository<RealEstateEnQualification, Long> {
}
