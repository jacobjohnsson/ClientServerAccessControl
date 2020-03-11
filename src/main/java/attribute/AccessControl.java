package attribute;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AccessControl {
  
  public abstract boolean hasAccess(Subject subject, ObjectControl.AccessRight right);
  
  public abstract String getId();
  
  public static AccessControl fetch(String objectID) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("./src/main/resources/objects.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return new NoAccessControl();
    }
    
    
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] attributes = line.split(", ");
      String[] subjects = attributes[2].split(" : ");
      String[] divisions = attributes[3].split(" : ");
      
      if (attributes[0].toLowerCase().trim().equals(objectID)) {
        return new ObjectControl(attributes[0].trim(),
                attributes[1].trim(),
                subjects,
                divisions);
      }
    }
    return new NoAccessControl();
  }
}
