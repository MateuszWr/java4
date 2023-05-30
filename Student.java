public class Student {

  private String FirstName = "";
  private String LastName = "";
  private int Age;

  public Student(String firstName, String lastName, int age) {
    FirstName = firstName;
    LastName = lastName;
    Age = age;
  }

  public String GetName() {return FirstName;}
  public int GetAge() {return Age;}

  public String ToString() {
    if (LastName != "") {
      return FirstName + " " + LastName + " " + Integer.toString(Age);
    }

    return FirstName + " " + Integer.toString(Age);
  }

  public static Student Parse(String str) {
    String[] data = str.split(" ");

    if (data.length == 2) {
      return new Student(data[0], "", Integer.parseInt(data[2]));
    }

    if (data.length == 3) {
      return new Student(data[0], data[1], Integer.parseInt(data[2]));
    }

    return new Student("Parse", "Error", -1);
  }
}
