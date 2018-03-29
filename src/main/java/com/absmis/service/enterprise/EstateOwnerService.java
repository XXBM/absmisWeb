package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.EstateOwner;
import com.absmis.repository.enterprise.EstateOwnerRepository;
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
public class EstateOwnerService extends BasicService<EstateOwner, Long> {
    @Autowired
    EstateOwnerRepository estateOwnerRepository;
    /*增加*/
    public void addEstateOwner(EstateOwner estateOwner){
        this.estateOwnerRepository.save(estateOwner);
    }

    /*修改*/
    public void updateEstateOwner(EstateOwner estateOwner){
        this.estateOwnerRepository.saveAndFlush(estateOwner);}

    /*findById*/
    public EstateOwner findById(Long id){
        return estateOwnerRepository.findOne(id);
    }

    /*删除*/
    public void deleteEstateOwner(Long id){
        this.estateOwnerRepository.delete(id);
    }

    //分页显示
    public Page<EstateOwner> findAll(Pageable pageable){
        return this.estateOwnerRepository.findAll(pageable);
    }


    /**
     * 多条件查询
     */
    public Page<EstateOwner> findBySepc(Specification<EstateOwner> specification, Pageable pageable) {
        return this.estateOwnerRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<EstateOwner> findBySepc(Specification<EstateOwner> specification) {
        return this.estateOwnerRepository.findAll(specification);
    }
    public List<EstateOwner> findBySepc(Specification<EstateOwner> specification, Sort sort) {
        return this.estateOwnerRepository.findAll(specification,sort);
    }

    public Specification<EstateOwner> queryName(String property){
        return new Specification<EstateOwner>() {
            @Override
            public Predicate toPredicate(Root<EstateOwner> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("name"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}