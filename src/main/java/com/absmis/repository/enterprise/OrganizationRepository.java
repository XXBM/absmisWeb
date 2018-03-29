package com.absmis.repository.enterprise;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.enterprise.Organization;
import org.springframework.stereotype.Repository;


@Repository
public interface OrganizationRepository extends MyRepository<Organization, Long> {
}
