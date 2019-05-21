package com.jhy.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by JHy on 2019/5/21.
 */
@RestController
@RequestMapping("test")
public class Test {

    @RequestMapping(value = "helloworld",method = RequestMethod.GET)
    public String hellowolrd(HttpServletRequest req){
        String ip =req.getRemoteAddr();
        String result = "hello world from "+ ip;
        return result;
    }

}
