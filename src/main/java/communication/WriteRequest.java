package communication;

import attribute.AccessControl;
import attribute.Subject;
import database.Database;

public class WriteRequest extends Request {
  
  private String content;
  
  public WriteRequest(Subject subject, AccessControl object, String description, String content) {
    super(subject, object, description);
    this.content = content;
  }
  
  @Override
  public void execute(Database db) {
    // TODO
    System.out.println("Write to database!");
  }
}
