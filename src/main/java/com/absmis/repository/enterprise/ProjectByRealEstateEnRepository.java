package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ProjectByRealEstateEn;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectByRealEstateEnRepository extends MyRepository<ProjectByRealEstateEn, Long> {
    ProjectByRealEstateEn findById(Long id);
}
