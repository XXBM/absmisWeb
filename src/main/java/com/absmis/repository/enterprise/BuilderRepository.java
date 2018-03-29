package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.Builder;
import org.springframework.stereotype.Repository;


@Repository
public interface BuilderRepository extends MyRepository<Builder, Long> {
}
