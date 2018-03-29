package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.Admin;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends MyRepository<Admin, Long> {
}
