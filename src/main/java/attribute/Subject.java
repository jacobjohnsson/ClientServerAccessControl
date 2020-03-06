package attribute;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Subject {
  private String id;
  private String name;
  private String occupation;
  private String division;
  
  public Subject(String id, String name, String occupation, String division) {
    this.id = id;
    this.name = name;
    this.occupation = occupation;
    this.division = division;
  }
  
  @Override
  public String toString() {
    return "ID: " + id
            + "\nName: " + name
            + "\nOcc: " + occupation
            + "\nDiv: " + division;
  }
  
  public static Subject fetch(String subjectID) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("./src/main/resources/subjects.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return new NoUser();
    }
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] attributes = line.split(", ");
      if (attributes[0].equals(subjectID)) {
        return new User(attributes[0].trim(),
                attributes[1].trim(),
                attributes[2].trim(),
                attributes[3].trim());
      }
    }
    return new NoUser();
  }
}
