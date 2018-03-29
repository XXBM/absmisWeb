package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.Project;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends MyRepository<Project, Long> {
}
