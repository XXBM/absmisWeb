package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.CheckedStatus;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckedStatusRepository extends MyRepository<CheckedStatus, Long> {
}
