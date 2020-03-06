package communication;

import attribute.AccessControl;
import attribute.NoAccessControl;
import attribute.Subject;
import database.Database;

public abstract class Request {
  private Subject subject;
  private AccessControl object;
  private String description;
  
  public Request(Subject subject, AccessControl object, String description) {
    this.subject = subject;
    this.object = object;
    this.description = description;
  }
  
  public abstract void execute(Database db);
  
  @Override
  public String toString() {
    return subject.toString() + " wants to " + description + " " + object.toString();
  }
  
  public static Request parseUserInput(String msg, String author) {
    Subject subject = Subject.fetch(author);
    String[] parts = msg.split(",");
    AccessControl object = parseObject(parts);
    
    Request result = new InvalidRequest(subject, object);
    if (parts.length == 2 && parts[0].toLowerCase().trim().equals("read")) {
      result = new ReadRequest(subject, object);
    }
    return result;
  }
  
  private static AccessControl parseObject(String[] content) {
    if (content.length > 1) {
      return AccessControl.fetch(content[1].trim());
    } else {
      return new NoAccessControl();
    }
  }
}
