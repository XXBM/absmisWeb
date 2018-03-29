package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ProjectState;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectStateRepository extends MyRepository<ProjectState, Long> {
}
