package services;


import model.User;
import model.Person;
import model.Exam;
import model.Grade;

import repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class GradeService {

        Scanner scanner = new Scanner(System.in);
        private GradeDAO gradeDAO;
        private ExamDAO examDAO;
        private ExamService examService;
        private UserService userService;

        //SUPER HARD
        //De datum staat automatisch op vandaag
        //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
        //Je gaat een Exam moeten oproepen van de bestaande lijst van examens, Eman mag dus niet op null staan
        //Je gaat een Person moeten terugvinden met User
        public void createGrade() {
                User user = userService.PickUser();
                Person person = user.getPerson();
                String fullName = person.getFirstName() + " " + person.getFamilyName();
                List<Exam> gradeable = examService.gradeableExams();
                System.out.println("Add a grade for " + fullName);
                System.out.println("Choose the correct exam:");
                int counter = 1;
                if (!gradeable.isEmpty()) {
                        for (Exam exam : gradeable) {
                                System.out.println(counter + ". " + "Exam: " + exam.getName());
                                counter++;
                        }
                } else System.out.println("No exams found.");
                int choice = scanner.nextInt();

                Exam examToBeGraded = gradeable.get(choice - 1);
                Grade grade = new Grade();

                grade.setPerson(person);
                grade.setExam(examToBeGraded);
                grade.setDate(LocalDate.now());


                boolean correctGradeValue = false;
                BigDecimal gradeValue = null;
                //Check for valid input
                while (!correctGradeValue) {
                        System.out.print("Enter grade: ");
                        gradeValue = scanner.nextBigDecimal();
                        if (gradeValue.compareTo(BigDecimal.valueOf(0)) >= 0
                                && gradeValue.compareTo(BigDecimal.valueOf(examToBeGraded.getTotal())) <= 0) {
                                grade.setGradeValue(gradeValue);
                                correctGradeValue = true;
                        }
                }
                grade.setGradeValue(gradeValue);
                scanner.nextLine();



                System.out.println("Enter comment:");
                grade.setComment(scanner.nextLine());
                System.out.println("Enter the internal comment:");
                grade.setInternalComment(scanner.nextLine());


                System.out.print("Absence? Y/N: ");
                String answer = scanner.nextLine();
                if (answer.equals("Y")) {
                        grade.setAbsent(true);
                } else grade.setAbsent(false);

                System.out.print("Postponed? Y/N: ");
                answer = scanner.nextLine();
                if (answer.equals("Y")) {
                        grade.setPostponed(true);
                } else grade.setPostponed(false);

                gradeDAO.addGrade(grade);
        }

        //EASY
        public void getOneGradeById() {
                System.out.print("Enter the id of the grade: ");
                Long gradeId = Long.valueOf(scanner.next());
                Grade grade = gradeDAO.getGradeById(gradeId);

                if (grade != null) {
                        System.out.println("Grade for exam {" + grade.getExam().getName() + "} is: " + grade.getGradeValue() + "/" + grade.getExam().getTotal());
                } else System.out.println("No grade found for id.");
        }

        //MEDIUM
        //Controleer eerst of de user niet 'null' is
        //Gebruik een user.getPerson methode, en maak een extra methode in je DAO/repository om resultaten op te vragen met person
        public List getAllGradeByPerson() {
                User user = userService.PickUser();
                List<Grade> allGradesPerson = new ArrayList<>();
                List<Grade> allGrades = gradeDAO.getAllGrades();

                //Check if not null
                if (user != null) {
                        int counter = 1;
                        Person person = user.getPerson();
                        String fullName = person.getFirstName() + " " + person.getFamilyName();
                        System.out.println("All grades of " + fullName + ":");
                        if (!allGrades.isEmpty()) {
                                for (Grade grade : allGrades) {
                                        if (grade.getPerson().getId() == (person.getId())) {
                                                System.out.println(counter + ". "
                                                        + "Exam: " + grade.getExam().getName() + " with grade: "
                                                        + grade.getGradeValue() + "/"
                                                        + grade.getExam().getTotal());
                                                allGradesPerson.add(grade);
                                                counter++;
                                        }
                                }
                                return allGradesPerson;
                        } else {
                                System.out.println("No grades found for " + fullName);
                        }

                } else System.out.println("No valid input.");

                return null;
        }

        //HARD
        //Controleer eerst of de user niet 'null' is
        //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
        //Enkel de gradeValue en de comment mogen aangepast worden
        //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
        public void updateGrade() {
                User user = userService.PickUser();

                //Check if not null
                if (user != null) {
                        Person person = user.getPerson();
                        String fullName = person.getFirstName() + " " + person.getFamilyName();
                        List<Grade> allGrades = gradeDAO.getAllGrades();
                        List<Grade> allGradesPerson = new ArrayList<>();

                        int counter = 1;

                        System.out.println("Which grade do you want to update?");
                        if (!allGrades.isEmpty()) {
                                for (Grade grade : allGrades) {
                                        if (grade.getPerson().getId() == (person.getId())) {
                                                System.out.println(counter + ". " + "Exam {" + grade.getExam().getName() + "} with grade: " + grade.getGradeValue() + "/" + grade.getExam().getTotal());
                                                allGradesPerson.add(grade);
                                                counter++;
                                        }
                                }
                        } else System.out.println("No grades found for " + fullName);

                        int choice = scanner.nextInt();

                        Grade UpdatingGrade = allGradesPerson.get(choice - 1); //index correction
                        System.out.print("Updating the following exam {" + UpdatingGrade.getExam().getName() + "} with grade: " + UpdatingGrade.getGradeValue() + "/" + UpdatingGrade.getExam().getTotal() + "? Y/N: ");
                        scanner.nextLine();
                        String answer = scanner.nextLine();
                        if (answer.equals("Y")) {
                                System.out.print("Enter new grade: ");
                                UpdatingGrade.setGradeValue(scanner.nextBigDecimal());
                                scanner.nextLine();
                                System.out.println("Enter new comment:");
                                UpdatingGrade.setComment(scanner.nextLine());
                                gradeDAO.updateGrade(UpdatingGrade);
                        }

                } else {
                        System.out.println("No valid input to update grades");
                }


        }

        //EASY
        //Controleer eerst of de user niet 'null' is
        //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
        public void deleteGrade() {
                User user = userService.PickUser();
                if (user != null) {
                        List<Grade> gradesOfPerson = getAllGradeByPerson();
                        Person person = user.getPerson();
                        String fullName = person.getFirstName() + " " + person.getFamilyName();
                        System.out.println("Enter the grade you want to delete of " + fullName);
                        int choice = scanner.nextInt();

                       Grade gradeToBeDeleted = gradesOfPerson.get(choice - 1);
                       System.out.print("Confirm you want to delete this grade: Exam {" + gradeToBeDeleted.getExam().getName() + "} with grade: " + gradeToBeDeleted.getGradeValue() + "/" + gradeToBeDeleted.getExam().getTotal() + "? Y/N: ");
                        scanner.nextLine();
                        String answer = scanner.nextLine();
                        if (answer.equals("Y")) {
                                gradeDAO.deleteGrade(gradeToBeDeleted);
                        } else {
                                System.out.println("No valid input to delete grades");
                        }
                } else {
                        System.out.println("No user input.");
                }
        }

        //----
        //extra private methodes hieronder


}
