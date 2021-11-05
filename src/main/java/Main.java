import model.Gender;
import model.Person;
import model.User;
import repositories.*;


public class Main {
    public static void main(String[] args) {

        CourseDAO courseDAO = new CourseDAO();

        ExamDAO examDAO = new ExamDAO();

        ModuleDAO moduleDAO = new ModuleDAO();

        PersonDAO personDAO = new PersonDAO();

        UserDAO userDAO = new UserDAO();


        EMF.getEMF();


        Person person1 = new Person().setFirstName("Mick").setFamilyName("Van den Dooren").setGender(Gender.MALE);
        Person person2 = new Person().setFirstName("Dorien").setFamilyName("Christiaens").setGender(Gender.FEMALE);


        //Create a Person (in comment if tested)
//      persondao.createPerson(person1);
//      persondao.createPerson(person2);
//      persondao.getAllPersons();

        //Create a Course and update (in comment if tested)
//        Course course1 = new Course().setActive(true).setDescription("Programming fundamentals - API - Databases - ...").setName("Java IoT");
//        courseDAO.createCourse(course1);
//        person1.setCourse(course1);
//        personDAO.updatePerson(person1);

        //Create a Module (in comment if tested)
//        model.Module module1 = new model.Module().setName("Python").setDescription("Learning to program in Python");
//        moduleDAO.createModule(module1);
        //Create an Exam (in comment if tested)
//        Exam exam1 = new Exam().setName("API Test").setDate(Date.valueOf(LocalDate.of(2021,11,5)));
//        examDAO.createExam(exam1);

        //Create a User (in comment if tested)
//        User user1 = new User().setLogin("Administrator").setActive(true);
//        userDAO.createUser(user1);


        System.out.println("End of main");
    }
}