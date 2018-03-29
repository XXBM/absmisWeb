package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.EstateOwner;
import org.springframework.stereotype.Repository;


@Repository
public interface EstateOwnerRepository extends MyRepository<EstateOwner, Long> {
}
