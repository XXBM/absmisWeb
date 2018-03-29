package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.Supervisor;
import org.springframework.stereotype.Repository;


@Repository
public interface SupervisorRepository extends MyRepository<Supervisor, Long> {
}
