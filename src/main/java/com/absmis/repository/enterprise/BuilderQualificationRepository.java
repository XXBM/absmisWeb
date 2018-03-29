package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.BuilderQualification;
import org.springframework.stereotype.Repository;




@Repository
public interface BuilderQualificationRepository extends MyRepository<BuilderQualification, Long> {
}
