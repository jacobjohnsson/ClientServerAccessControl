package communication;

import attribute.AccessControl;
import attribute.Subject;
import database.Database;

public class ReadRequest extends Request {
  public ReadRequest(Subject subject, AccessControl object) {
    super(subject, object, "read");
  }
  
  @Override
  public void execute(Database db) {
    // TODO
    System.out.println("Read from database!");
  }
}
