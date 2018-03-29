package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.RealEstateEnQualification;
import com.absmis.service.enterprise.RealEstateEnQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RealEstateEnQualificationController {
    @Autowired
    RealEstateEnQualificationService realEstateEnQualificationService;



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllRealEstateEnQualifications", method = RequestMethod.GET)
    public List<RealEstateEnQualification> findRealEstateEnQualification()throws Exception {
        List<RealEstateEnQualification> realEstateEnQualifications = realEstateEnQualificationService.findAllT();
        return realEstateEnQualifications;
    }
}
