package attribute;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.File;

public class Subject {
  private String id;
  private String name;
  private String occupation;
  private String division;

  public Subject (String id, String name, String occupation, String division) {
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

  public static List<Subject> load(String file) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(file));
    List<Subject> subjects = new LinkedList<Subject>();
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] attributes = line.split(", ");
      Subject s = new Subject(attributes[0].trim(),
                  attributes[1].trim(),
                  attributes[2].trim(),
                  attributes[3].trim());
      subjects.add(s);
    }
    return subjects;
  }

}
