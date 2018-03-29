package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.ConstructionEnIndustrialization;
import com.absmis.domain.enterprise.Designer;
import com.absmis.domain.enterprise.RealEstateEn;
import com.absmis.repository.enterprise.DesignerRepository;
import com.absmis.service.BasicService;
import com.absmis.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class DesignerService extends BasicService<Designer, Long> {
    @Autowired
    DesignerRepository designerRepository;
    /*增加*/
    public void addDesigner(Designer designer){
        this.designerRepository.save(designer);
    }

    /*修改*/
    public void updateDesigner(Designer designer){
        this.designerRepository.saveAndFlush(designer);}

    /*findById*/
    public Designer findById(Long id){
        return designerRepository.findOne(id);
    }

    /*删除*/
    public void deleteDesigner(Long id){
        this.designerRepository.delete(id);
    }

    //分页显示
    public Page<Designer> findAll(Pageable pageable){
        return this.designerRepository.findAll(pageable);
    }

    /**
     * 多条件查询
     */
    public Page<Designer> findBySepc(Specification<Designer> specification, Pageable pageable) {
        return this.designerRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<Designer> findBySepc(Specification<Designer> specification) {
        return this.designerRepository.findAll(specification);
    }
    public List<Designer> findBySepc(Specification<Designer> specification, Sort sort) {
        return this.designerRepository.findAll(specification,sort);
    }

    public Specification<Designer> queryName(String property){
        return new Specification<Designer>() {
            @Override
            public Predicate toPredicate(Root<Designer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("name"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    public Specification<Designer> queryByAnnualCumulant(
            Integer year,
            Integer quarter
    ){
        return new Specification<Designer>(){
            @Override
            public Predicate toPredicate(Root<Designer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.equal(root.get("cumulant"),0));
                Join<RealEstateEn, ConstructionEnIndustrialization> realEstateEnAssIndustrializationJoins = root.join("constructionEnIndustrializations");
                predicate.add(cb.greaterThan(realEstateEnAssIndustrializationJoins.get("totalScale"),0));
                predicate.add(cb.lessThanOrEqualTo(realEstateEnAssIndustrializationJoins.get("quarterEnd").as(String.class), Utils.getQuarterEndTime(year,quarter)));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public Specification<Designer> queryByCumulant(){
        return new Specification<Designer>() {
            @Override
            public Predicate toPredicate(Root<Designer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.greaterThan(root.get("cumulant"),0));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


}