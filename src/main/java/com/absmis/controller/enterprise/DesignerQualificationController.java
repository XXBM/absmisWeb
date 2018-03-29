package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.DesignerQualification;
import com.absmis.service.enterprise.DesignerQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DesignerQualificationController {
    @Autowired
    DesignerQualificationService designerQualificationService;



    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllDesignerQualifications", method = RequestMethod.GET)
    public List<DesignerQualification> findDesignerQualification()throws Exception {
        List<DesignerQualification> designerQualifications = designerQualificationService.findAllT();
        return designerQualifications;
    }
}
