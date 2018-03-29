package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ComponentEn;
import org.springframework.stereotype.Repository;


@Repository
public interface ComponentEnRepository extends MyRepository<ComponentEn, Long> {
}
