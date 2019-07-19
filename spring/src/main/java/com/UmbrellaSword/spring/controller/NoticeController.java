package com.ruolan.spring.controller;


import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruolan.spring.pojo.Notice;
import com.ruolan.spring.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @RequestMapping("/Ifnew")
    @ResponseBody
    public String ifnew(HttpServletRequest httpServletRequest) throws SQLException {
        String Userid=httpServletRequest.getParameter("Userid");
        return noticeService.ifnew(Userid);
    }

    @RequestMapping("/selectNotice")
    @ResponseBody
    public Map<String,Object> selectNotice(HttpServletRequest httpServletRequest){
        String Userid=httpServletRequest.getParameter("Userid");
        String Type=httpServletRequest.getParameter("Type");
        Map<String,Object> map = new HashMap<>();
        List<Notice> noticeList = noticeService.selectNotice(Userid,Type);
        map.put("code",0);
        map.put("data",noticeList);
        map.put("count",noticeList.size());
        int newnum = 0;
        for(Notice notice:noticeList){
            if(notice.getIfnew().equals("是"))
                newnum ++;
        }
        map.put("newnum",newnum);
        return map;
    }
    @RequestMapping("/magEvent")
    @ResponseBody
    public Map<String,String> magEvent(HttpServletRequest httpServletRequest){
        Gson gson=new Gson();
        Map<String,String> map = new HashMap<>();
        boolean code = true;
        String event=httpServletRequest.getParameter("event");
        String userid = httpServletRequest.getParameter("Userid");
        String type=httpServletRequest.getParameter("type");
        String noticeid=httpServletRequest.getParameter("Noticeid");
        if(event.equals("readyAll")||event.equals("readyone"))
        {
            if(event.equals("readyAll"))
                code = noticeService.UpdatenoticereadyAll(userid,type);
            else
                code = noticeService.Updatenotice(noticeid);
            if(code)
            {
                map.put("code","1");
                map.put("msg","已读成功");
            }
            else
            {
                map.put("code","0");
                map.put("msg","已读失败");
            }
            String json1=gson.toJson(map);
            return map;
        }
        String json=httpServletRequest.getParameter("json");
        System.out.println(json + "   " + event);
        List<Notice> noticeList = (List<Notice>) JSONArray.parseArray(json,Notice.class);
        /*Type noticeListType = new TypeToken<ArrayList<Notice>>(){}.getType();
        List<Notice> noticeList = gson.fromJson(json, noticeListType);*/
        for(Notice obj:noticeList)
        {
            if(event.equals("del"))
                code = code && noticeService.DelNotice(obj.getNoticeid());
            else if(event.equals("ready"))
                code = code && noticeService.Updatenotice(obj.getNoticeid()+"");
        }
        if(code)
        {
            map.put("code","1");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("code","0");
            map.put("msg","删除失败");
        }
        return map;
    }
    @RequestMapping("/insertNotice")
    @ResponseBody
    public String insertNotice(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        String topic = httpServletRequest.getParameter("topic");
        String type = httpServletRequest.getParameter("type");
        String stuid = httpServletRequest.getParameter("stuid");
        String content = httpServletRequest.getParameter("content");
        return noticeService.insertNotice(id,topic,type,stuid,content)+"";
    }
}
