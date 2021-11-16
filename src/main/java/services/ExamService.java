package services;

import repositories.ExamDAO;
import repositories.GradeDAO;
import model.Exam;
import model.Grade;

import java.util.ArrayList;
import java.util.List;

public class ExamService {
    ExamDAO examDAO = new ExamDAO();
    GradeDAO gradeDAO = new GradeDAO();

    public void outputExam(Long id) {
        Exam exam = examDAO.findExamById(id);
        printGradeForExamWithoutSubExams(exam);
    }

    private void printGradeForExamWithoutSubExams(Exam exam) {
        List<Grade> listOfAllGrades = gradeDAO.getAllGrades();

        //check exam for sub-exams
        if (exam.getSubExams().isEmpty()) {
            //exam without filled in column (is no parent)
            System.out.println(exam.getName() + " has following grades:");

            for (Grade grade : listOfAllGrades) {

                if (grade.getExam().getId().equals(exam.getId())) {
                    System.out.print(grade.getGradeValue() + " : " + exam.getTotal() + ";");
                }
            }

        } else {
            //exam with filled in column (is parent)
            System.out.println(exam.getName() + " is a parent exam.");
            List<Exam> listOfSubExams = exam.getSubExams();
            for (Exam subExam : listOfSubExams) {
                printGradeForExamWithoutSubExams(subExam);
            }

        }

    }

    public List<Exam> gradeableExams() {
        List<Exam> allExams = examDAO.getAllExams();
        List<Exam> gradableExamList = new ArrayList<>();
        for (Exam exam : allExams) {
            if (exam.getSubExams().isEmpty()) {
                gradableExamList.add(exam);
            }
        }
        return gradableExamList;

    }

}