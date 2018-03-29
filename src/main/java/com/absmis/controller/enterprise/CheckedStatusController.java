package com.absmis.controller.enterprise;

import com.absmis.domain.enterprise.CheckedStatus;
import com.absmis.service.enterprise.CheckedStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckedStatusController {
    @Autowired
    CheckedStatusService checkedStatusService;
    /**
     * 获取到所有
     */
    @RequestMapping(value = "/findAllCheckedStates", method = RequestMethod.GET)
    public List<CheckedStatus> findCheckedState()throws Exception {
        List<CheckedStatus> checkedStatuses = checkedStatusService.findAllT();
        return checkedStatuses;
    }
}
