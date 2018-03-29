package com.absmis.repository.authority;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.authority.RoleAssResource;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleAssResourceRepository extends MyRepository<RoleAssResource, Long> {
}