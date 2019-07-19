package com.ruolan.spring.pojo;

import org.springframework.stereotype.Component;

@Component
public class Test {
    int id;
    int type;
    String question;
    String answer;
    String answerA;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    String answerB;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String answerC;
    String answerD;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
}
