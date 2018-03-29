package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.ProjectCategory;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectCategoryRepository extends MyRepository<ProjectCategory, Long> {
}
