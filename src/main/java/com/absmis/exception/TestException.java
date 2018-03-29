package com.absmis.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestException {
    @RequestMapping("/testJson")
    public  String testJson() throws JsonException {
        throw new JsonException("json发送失败！");
    }
    @RequestMapping("/test404")
    public  String test4() throws Exception{
      throw new Exception("请求的不存在");
    }
}
