/*
Kod bazowy programu Commit4_0:
• Program dodaje do prostej bazy danych (pliku db.txt) dane odnośnie Studentów.
• Studenci dodawani są w klasie Main.
• Wszyscy studenci są wypisywani na końcu klasy Main.
• Klasa Service obsługuje odczyt i zapis do pliku bazy danych.
• Klasa Student reprezentuje pojedynczego studenta (Imię, Wiek).
*/

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

class Main {
  public static enum ACTIONS {
    dodaj, lista, koniec
  };

  public static String HELP_MSG = "Wybierz z listy dostępnych opcji (dodaj, lista, koniec):\n";
  public static String SELECT_FIRST_NAME_MSG = "Podaj imię:";
  public static String SELECT_LAST_NAME_MSG = "Podaj nazwisko:";
  public static String SELECT_AGE_MSG = "Podaj wiek:";
  public static String STUDENT_WAS_ADDED_MSG = "Student został dodany pomyślnie";
  public static String UNKNOWN_ACTION_MSG = "Nieznana akcja";

  public static void listAllStudents(Service service) throws IOException {
    System.out.println("");
    Collection<Student> students = service.getStudents();
    int i = 1;

    for(Student current : students) {
      System.out.println(i + ": " + current.ToString());
      i++;
    }

    System.out.println("");
  }

  public static void addNewStudent(Service service, Scanner scanner) throws IOException {
    System.out.println("");
    String firstName = "";
    String lastName = "";
    int age = -1;

    while(firstName == "") {
      System.out.println(SELECT_FIRST_NAME_MSG);
      firstName = scanner.next().trim();
    }

    while(lastName == "") {
      System.out.println(SELECT_LAST_NAME_MSG);
      lastName = scanner.next().trim();
    }

    while(age < 0) {
      System.out.println(SELECT_AGE_MSG);
      age = scanner.nextInt();
    }

    Student student = new Student(firstName, lastName, age);
    service.addStudent(student);
    System.out.println(STUDENT_WAS_ADDED_MSG);
  }

  public static void main(String[] args) {
    try {
      Service service = new Service();
      boolean stillWork = true;

      while(stillWork) {
        System.out.println(HELP_MSG);
        Scanner scanner = new Scanner(System.in);
        String action = scanner.next().trim();

        switch (ACTIONS.valueOf(action)) {
          case dodaj:
            addNewStudent(service, scanner);
            break;
          case lista:
            listAllStudents(service);
            break;
          case koniec:
            stillWork = false;
            break;
          default:
            System.out.println(UNKNOWN_ACTION_MSG);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
