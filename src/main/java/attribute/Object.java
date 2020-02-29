package attribute;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.File;

public class Object {
  private String id;
  private String name;
  private String[] subjects;
  private String[] divisions;

  public Object (String id, String name, String[] subjects, String[] divisions) {
    this.id = id;
    this.name = name;
    this.subjects = subjects;
    this.divisions = divisions;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: " + id);
    sb.append("\nName: " + name);
    sb.append("\nSubjects: ");
    for (String sub : subjects) {
      sb.append(", " + sub);
    }
    sb.append("\nDivisions: ");
    for (String div : divisions) {
      sb.append(", " + div);
    }
    return sb.toString();
  }

  public static List<Object> load(String file) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(file));
    List<Object> objects = new LinkedList<Object>();
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] attributes = line.split(", ");
      String[] subjects = attributes[2].split(" : ");
      String[] divisions = attributes[3].split(" : ");
      Object o = new Object(attributes[0].trim(),
                  attributes[1].trim(),
                  subjects,
                  divisions);
      objects.add(o);
    }
    return objects;
  }
}
