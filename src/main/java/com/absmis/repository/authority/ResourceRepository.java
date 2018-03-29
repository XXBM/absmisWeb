package com.absmis.repository.authority;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.authority.Resource;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceRepository extends MyRepository<Resource,Long> {
}
