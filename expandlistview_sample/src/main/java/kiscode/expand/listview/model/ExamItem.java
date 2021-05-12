package kiscode.expand.listview.model;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/4/14 13:07
 **/
public class ExamItem {

    /**
     * Id : 21643
     * TypeName : SingleSelect
     * Question : 对于题目xxxx，请选择正确答案
     * UserAnswer : null
     * Answer : null
     * ExamItemInfoList : [{"Id":76665,"Name":"A","Content":"50ft"},{"Id":76666,"Name":"B","Content":"100ft"},{"Id":76687,"Name":"C","Content":"120ft "},{"Id":76688,"Name":"D","Content":"150ft"}]
     */

    private Integer Id;
    private String TypeName;
    private String Question;
    private String UserAnswer;
    private String Answer;
    private List<ExamAnswerItem> ExamItemInfoList;

    public static class ExamAnswerItem {
        /**
         * Id : 76665
         * Name : A
         * Content : 50ft
         */

        private Integer Id;
        private String Name;
        private String Content;
        private boolean check;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getUserAnswer() {
        return UserAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        UserAnswer = userAnswer;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public List<ExamAnswerItem> getExamItemInfoList() {
        return ExamItemInfoList;
    }

    public void setExamItemInfoList(List<ExamAnswerItem> examItemInfoList) {
        ExamItemInfoList = examItemInfoList;
    }
}