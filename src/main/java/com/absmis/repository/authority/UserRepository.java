package com.absmis.repository.authority;

import com.absmis.JpaRepository.MyRepository;
import com.absmis.domain.authority.User;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends MyRepository<User, Long> {
    User findByUsername(String username);
}
