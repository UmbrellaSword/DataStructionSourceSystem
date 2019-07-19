package com.ruolan.spring.controller;

import com.ruolan.spring.dao.ResourceDao;
import com.ruolan.spring.pojo.Source;
import com.ruolan.spring.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("Resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;
    @RequestMapping("/insertResource")
    @ResponseBody
    public String insertResource(HttpServletRequest httpServletRequest){
        String res_name = httpServletRequest.getParameter("name");
        String teacher_id = httpServletRequest.getParameter("teacher_id");
        return resourceService.insertResource(res_name,teacher_id)+"";
    }

    @RequestMapping("selectResource")
    @ResponseBody
    public List<Source> selectResource(){
        return resourceService.selectResource();
    }

    @RequestMapping("insertMyResource")
    @ResponseBody
    public boolean insertMyResource(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");
        return resourceService.insertMyResource(id,name);
    }

    @RequestMapping("selectMyResource")
    @ResponseBody
    public List<Source> selectMyResource(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        return resourceService.selectMyResource(id);
    }

    @RequestMapping("selectUptodateSource")
    @ResponseBody
    public List<Source> selectUptodateSource(){
        return resourceService.selectUptodateSource();
    }
}
