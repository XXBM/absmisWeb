package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.Supervisor;
import com.absmis.repository.enterprise.SupervisorRepository;
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
public class SupervisorService extends BasicService<Supervisor, Long> {
    @Autowired
    SupervisorRepository supervisorRepository;

    /*增加*/
    public void addSupervisor(Supervisor supervisor){
        this.supervisorRepository.save(supervisor);
    }

    /*修改*/
    public void updateSupervisor(Supervisor supervisor){
        this.supervisorRepository.saveAndFlush(supervisor);}

    /*findById*/
    public Supervisor findById(Long id){
        return supervisorRepository.findOne(id);
    }

    /*删除*/
    public void deleteSupervisor(Long id){
        this.supervisorRepository.delete(id);
    }

    //分页显示
    public Page<Supervisor> findAll(Pageable pageable){
        return this.supervisorRepository.findAll(pageable);
    }


    /**
     * 多条件查询
     */
    public Page<Supervisor> findBySepc(Specification<Supervisor> specification, Pageable pageable) {
        return this.supervisorRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<Supervisor> findBySepc(Specification<Supervisor> specification) {
        return this.supervisorRepository.findAll(specification);
    }
    public List<Supervisor> findBySepc(Specification<Supervisor> specification, Sort sort) {
        return this.supervisorRepository.findAll(specification,sort);
    }


    public Specification<Supervisor> queryName(String property){
        return new Specification<Supervisor>() {
            @Override
            public Predicate toPredicate(Root<Supervisor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //条件一：查询在岗人员
                predicate.add(cb.like(root.get("username"),"%"+property+"%"));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}