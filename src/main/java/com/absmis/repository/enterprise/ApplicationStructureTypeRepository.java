package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ApplicationStructureType;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationStructureTypeRepository extends MyRepository<ApplicationStructureType, Long> {
}
