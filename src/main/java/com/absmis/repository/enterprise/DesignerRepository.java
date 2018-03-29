package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.Designer;
import org.springframework.stereotype.Repository;


@Repository
public interface DesignerRepository extends MyRepository<Designer, Long> {
}
