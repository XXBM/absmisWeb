package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.Organization;
import com.absmis.repository.enterprise.OrganizationRepository;
import com.absmis.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrganizationService extends BasicService<Organization, Long> {
    @Autowired
    OrganizationRepository organizationRepository;
    /**
     * 多条件查询
     */
    public Page<Organization> findBySepc(Specification<Organization> specification, Pageable pageable) {
        return this.organizationRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<Organization> findBySepc(Specification<Organization> specification) {
        return this.organizationRepository.findAll(specification);
    }
    public List<Organization> findBySepc(Specification<Organization> specification,Sort sort) {
        return this.organizationRepository.findAll(specification,sort);
    }

    public Specification<Organization> findNoTra(List<Long> property){
        return new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.in(root.get("id")).value(property));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public Specification<Organization> findNoTraAndQueryName(
            List<Long> property,
            String name){
        return new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.in(root.get("id")).value(property));
                predicate.add(cb.like(root.get("name"),"%"+name+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}