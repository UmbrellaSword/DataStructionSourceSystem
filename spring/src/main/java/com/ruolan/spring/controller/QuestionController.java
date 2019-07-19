package com.ruolan.spring.controller;

import com.ruolan.spring.pojo.Question;
import com.ruolan.spring.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("Question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @RequestMapping("/insertQuestion")
    @ResponseBody
    public String insertQuestion(HttpServletRequest httpServletRequest){
        Question question = new Question();
        question.setStudent_id(httpServletRequest.getParameter("id"));
        question.setContent(httpServletRequest.getParameter("content"));
        question.setTopic(httpServletRequest.getParameter("topic"));
        question.setTeacher_id(Integer.parseInt(httpServletRequest.getParameter("teacher")));
        return questionService.insertQuestion(question)+"";
    }
    @RequestMapping("/insertAnswer")
    @ResponseBody
    public String insertAnswer(HttpServletRequest httpServletRequest){
        String questionid=httpServletRequest.getParameter("questionid");
        String response=httpServletRequest.getParameter("response");
        return questionService.insertAnswer(response,questionid)+"";
    }

    @RequestMapping("/selectQuestion")
    @ResponseBody
    public List<Question> selectQuestion(HttpServletRequest httpServletRequest){
        return questionService.selectQuestion(Integer.parseInt(httpServletRequest.getParameter("id")));
    }

    @RequestMapping("/selectMyQuestion")
    @ResponseBody
    public List<Question> selectMyQuestion(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        return questionService.selectMyQuestion(id);
    }
    @RequestMapping("/selectNotNullQuestion")
    @ResponseBody
    public List<Question> selectNotNullQuestion(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        return  questionService.selectNotNullQuestion(id);
    }
}
