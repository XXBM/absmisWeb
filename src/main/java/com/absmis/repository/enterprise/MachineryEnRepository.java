package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.MachineryEn;
import org.springframework.stereotype.Repository;


@Repository
public interface MachineryEnRepository extends MyRepository<MachineryEn, Long> {
}
