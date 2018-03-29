package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.BuilderQualification;
import com.absmis.service.enterprise.BuilderQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuilderQualificationController {
    @Autowired
    BuilderQualificationService builderQualificationService;



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllBuilderQualifications", method = RequestMethod.GET)
    public List<BuilderQualification> findBuilderQualification()throws Exception {
        List<BuilderQualification> builderQualifications = builderQualificationService.findAllT();
        return builderQualifications;
    }
}
