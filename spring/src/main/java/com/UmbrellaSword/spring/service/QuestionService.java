package com.ruolan.spring.service;

import com.ruolan.spring.dao.QuestionDao;
import com.ruolan.spring.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public boolean insertQuestion(Question question){
        if(questionDao.insertQuestion(question)>0){
            return  true;
        }
        else
            return false;
    }

    public boolean insertAnswer(String response,String questionid){
        if(questionDao.insertAnswer(response,questionid)>0)
            return true;
        else
            return false;
    }

    public List<Question> selectQuestion(int id){
        List<Question> questionList = new LinkedList<>();
        questionList = questionDao.selectQuestion(id);
        for(Question question : questionList){
            question.setStudent_id(selectStudentNameById(question.getStudent_id()));
        }
        return questionList;
    }

    public List<Question> selectMyQuestion(String id){
        List<Question> questionList = questionDao.selectMyQuestion(id);
        for(Question question : questionList){
            question.setStudent_id(selectStudentNameById(question.getStudent_id()));
        }
        return questionList;
    }

    public String selectStudentNameById(String id){
        return questionDao.selectStudentNameById(id);
    }

    public List<Question> selectNotNullQuestion(String id) {
        List<Question> questionList = new LinkedList<>();
        questionList = questionDao.selectNotNullQuestion(id);
        for(Question question : questionList){
            question.setStudent_id(selectStudentNameById(question.getStudent_id()));
        }
        return questionList;
    }
}
