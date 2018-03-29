package com.absmis.repository.authority;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.authority.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MyRepository<Role,Long> {

}
