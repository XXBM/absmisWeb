package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ConstructionEn;
import com.absmis.repository.enterprise.ConstructionEnRepository;
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
public class ConstructionEnService extends BasicService<ConstructionEn, Long> {
    @Autowired
    ConstructionEnRepository constructionEnRepository;
    /**
     * 多条件查询
     */
    public Page<ConstructionEn> findBySepc(Specification<ConstructionEn> specification, Pageable pageable) {
        return this.constructionEnRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<ConstructionEn> findBySepc(Specification<ConstructionEn> specification) {
        return this.constructionEnRepository.findAll(specification);
    }
    public List<ConstructionEn> findBySepc(Specification<ConstructionEn> specification,Sort sort) {
        return this.constructionEnRepository.findAll(specification,sort);
    }


    public Specification<ConstructionEn> queryName(String property){
        return new Specification<ConstructionEn>() {
            @Override
            public Predicate toPredicate(Root<ConstructionEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("name"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<ConstructionEn> queryAnnual(
            Long enId,
            Integer year,
            Integer quarter){
        return new Specification<ConstructionEn>() {
            @Override
            public Predicate toPredicate(Root<ConstructionEn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("subUnitEn"), enId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}