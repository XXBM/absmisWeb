package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.UnitEngineering;
import com.absmis.repository.enterprise.UnitEngineeringRepository;
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
public class UnitEngineeringService extends BasicService<UnitEngineering, Long> {
    @Autowired
    UnitEngineeringRepository unitEngineeringRepository;

    public Page<UnitEngineering> findByProjectId(Long id, Pageable pageable) {
        return this.unitEngineeringRepository.findByProjectId(id, pageable);
    }
    public List<UnitEngineering> findByProjectId(Long id) {
        return this.unitEngineeringRepository.findByProjectId(id);
    }
    public List<UnitEngineering> findByStructureFormId(Long id) {
        return this.unitEngineeringRepository.findByStructureFormId(id);
    }

    /*增加*/
    public void addUnitEngineering(UnitEngineering unitEngineering){
        this.unitEngineeringRepository.save(unitEngineering);
    }

    /*修改*/
    public void updateUnitEngineering(UnitEngineering unitEngineering){
        this.unitEngineeringRepository.saveAndFlush(unitEngineering);}

    /*findById*/
    public UnitEngineering findById(Long id){
        return unitEngineeringRepository.findOne(id);
    }

    /*删除*/
    public void deleteUnitEngineering(Long id){
        this.unitEngineeringRepository.delete(id);
    }

    //分页显示
    public Page<UnitEngineering> findAll(Pageable pageable){
        return this.unitEngineeringRepository.findAll(pageable);
    }


    /**
     * 多条件查询
     */
    public Page<UnitEngineering> findBySepc(Specification<UnitEngineering> specification, Pageable pageable) {
        return this.unitEngineeringRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<UnitEngineering> findBySepc(Specification<UnitEngineering> specification) {
        return this.unitEngineeringRepository.findAll(specification);
    }
    public List<UnitEngineering> findBySepc(Specification<UnitEngineering> specification, Sort sort) {
        return this.unitEngineeringRepository.findAll(specification,sort);
    }


    public Specification<UnitEngineering> queryQuarter(
            Long categoryId,
            Integer year,
            Integer quarter){
        return new Specification<UnitEngineering>() {
            @Override
            public Predicate toPredicate(Root<UnitEngineering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("engineeringIndustrialization").get("applicationStructureType"), categoryId));
                predicate.add(cb.equal(root.get("year"), year));
                predicate.add(cb.lessThanOrEqualTo(root.get("quarter"), quarter));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<UnitEngineering> queryQuarterYear(
            Long categoryId,
            Integer year){
        return new Specification<UnitEngineering>() {
            @Override
            public Predicate toPredicate(Root<UnitEngineering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("engineeringIndustrialization").get("applicationStructureType"), categoryId));
                predicate.add(cb.lessThan(root.get("year"), year));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<UnitEngineering> queryQuarter(
            Long categoryId,
            String endTime){
        return new Specification<UnitEngineering>() {
            @Override
            public Predicate toPredicate(Root<UnitEngineering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("engineeringIndustrialization").get("applicationStructureType"), categoryId));
                predicate.add(cb.lessThanOrEqualTo(root.get("startingTime").as(String.class), endTime));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}