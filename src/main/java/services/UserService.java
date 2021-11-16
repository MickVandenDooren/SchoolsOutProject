package services;

import model.Gender;
import model.User;
import model.Person;
import repositories.PersonDAO;
import repositories.UserDAO;


import java.util.List;
import java.util.Scanner;

public class UserService {


    Scanner scanner = new Scanner(System.in);
    UserDAO userDAO = new UserDAO();
    User cacheUser = new User();

    //HARD
    //1.Zorg ervoor dat een persoon ook aangemaakt wordt
    //2.Maak een controle methode die vraagt om een gebruiker een passwoord twee keer in te geven
    //3. Het wachtwoord wordt hier geincrypteerd
    public void createUser(){
        System.out.println("Enter your first name:");
        String firstName = scanner.next();
        System.out.println("Enter your last name:");
        String lastName = scanner.next();
        System.out.println("Are you...");
        System.out.println("0. Male");
        System.out.println("1. Female");
        System.out.println("2. Other");
        int genderChoice = scanner.nextInt();

        System.out.println("Making new Profile..."+"\n");

        Scanner sc = new Scanner(System.in);
        String password;
        String reentered;
        do {
            System.out.println("(Re)Enter Password for the following user ("+firstName+"."+lastName+"):");
            password = sc.next();
            System.out.println("Confirm Password:");
            reentered = sc.next();
        } while (!password.equals(reentered));

        Person person = new Person()
                .setFirstName(firstName)
                .setFamilyName(lastName);
        User user = new User()
                .setPerson(person)
                .setLogin(firstName+"."+lastName)
                .setActive(true)
                .setPasswordHash(password);

        if(genderChoice==0){
            person.setGender(Gender.MALE);
        } else if(genderChoice==1) {
            person.setGender(Gender.FEMALE);
        } else {
            person.setGender(Gender.OTHER);
        }

        UserDAO.createUser(user);
        cacheUser = user;
    }

    //EASY
    //Als er geen User terug gegeven wordt, stuur als bericht "User does not exist"
    public User getOneUserByName(){
        System.out.print("Enter a username: ");
        String username = scanner.next();
        User user = userDAO.findUserByLogin(username);
        cacheUser = user;
        if (user != null) {
            System.out.println("Info on the following user: " + user.getLogin());
            System.out.println("Name: " +user.getPerson().getFirstName() +" "+user.getPerson().getFamilyName());
            System.out.println("Gender: " +user.getPerson().getGender());
            System.out.println("Active status: "+ user.getActive());
        } else System.out.println("User does not exist");
        return user;
    }

    //EASY
    //print een lijst uit van alle users.
    public void getAllUsers(){
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    //EASY
    //Een username mag niet aangepast worden
    //Bonus HARD
    //De Person hoef je niet te updaten (als je dat wilt, doe je dat best via een aparte personservice,
    // via een aprte updatePersonMethode)
    public void updateUser(){
        getAllUsers();
        System.out.println("Choose the login of which user you want to update:");
        String login = scanner.next();
        User user = userDAO.findUserByLogin(login);
        System.out.println("Enter password of the user you want to update:");
        String password = scanner.next();

        if (user.getPasswordHash().equals(password)){
            System.out.println("Update information of the following user " +user+":");

            //ACTIVE STATUS
            System.out.println("Set user as active? (Y/N)");
            String activation = scanner.next();

            if(activation.equals("Y")){
                user.setActive(Boolean.TRUE);
            } else if (activation.equals("N")) {
                user.setActive(Boolean.FALSE);
            } else {
                System.out.println("Active status remains unchanged.");
            }
            //


            //Password change
            System.out.println("Do you want to change your password? (Y/N)");
            String passwordActivation = scanner.next();

            if(passwordActivation.equals("Y")){
                Scanner sc = new Scanner(System.in);
                String newPassword;
                String newPasswordreentered;
                do {
                    System.out.println("(Re)Enter new Password for the following user ("+user.getLogin()+"):");
                    newPassword = sc.next();
                    System.out.println("Confirm new Password:");
                    newPasswordreentered = sc.next();
                } while (!newPassword.equals(newPasswordreentered));

                user.setPasswordHash(newPassword);

            } else {
                System.out.println("Password remains unchanged.");
            }
            //



            userDAO.updateUser(user);
            System.out.println("User has been updated.");
            cacheUser = user;
        } else {
            System.out.println("Incorrect username or password.");
        }
    }

    //MEDIUM
    //Vraag de User een passwoord in te geven voor dat hij zijn account kan verwijderen.
    //De Person moet ook mee gedeleted worden
    public void deleteUser(){
        getAllUsers();
        System.out.println("Choose the login of which user to delete:");
        String login = scanner.next();
        User user = userDAO.findUserByLogin(login);
        System.out.println("Enter password of the user you want to delete:");
        String password = scanner.next();

        if (user.getPasswordHash().equals(password)){
            userDAO.deleteUser(user);
            System.out.println("User has been deleted.");
        } else {
            System.out.println("Incorrect username or password. ");
        }

    }

    public User PickUser(){
        getAllUsers();
        System.out.println("Enter login of the user:");
        String login = scanner.next();
        return userDAO.findUserByLogin(login);
    }
}
