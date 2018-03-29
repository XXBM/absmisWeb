package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.StructureForm;
import org.springframework.stereotype.Repository;


@Repository
public interface StructureFormRepository extends MyRepository<StructureForm, Long> {
}
