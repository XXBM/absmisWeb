package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ProjectByEstateOwner;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectByEstateOwnerRepository extends MyRepository<ProjectByEstateOwner, Long> {
}
