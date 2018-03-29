package com.absmis.service.enterprise;


import com.absmis.domain.enterprise.Admin;
import com.absmis.repository.enterprise.AdminRepository;
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
public class AdminService extends BasicService<Admin, Long> {
    @Autowired
    AdminRepository adminRepository;

    /*增加*/
    public void addAdmin(Admin admin){
        this.adminRepository.save(admin);
    }

    /*修改*/
    public void updateAdmin(Admin admin){
        this.adminRepository.saveAndFlush(admin);}

    /*findById*/
    public Admin findById(Long id){
        return adminRepository.findOne(id);
    }

    /*删除*/
    public void deleteAdmin(Long id){
        this.adminRepository.delete(id);
    }

    //分页显示
    public Page<Admin> findAll(Pageable pageable){
        return this.adminRepository.findAll(pageable);
    }


    /**
     * 多条件查询
     */
    public Page<Admin> findBySepc(Specification<Admin> specification, Pageable pageable) {
        return this.adminRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<Admin> findBySepc(Specification<Admin> specification) {
        return this.adminRepository.findAll(specification);
    }
    public List<Admin> findBySepc(Specification<Admin> specification, Sort sort) {
        return this.adminRepository.findAll(specification,sort);
    }


    public Specification<Admin> queryName(String property){
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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