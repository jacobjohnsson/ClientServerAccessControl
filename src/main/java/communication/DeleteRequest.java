package communication;

import attribute.AccessControl;
import attribute.Subject;
import database.Database;

public class DeleteRequest extends Request {
  public DeleteRequest(Subject subject, AccessControl object, String description) {
    super(subject, object, description);
  }
  
  @Override
  public void execute(Database db) {
    // TODO
    System.out.println("Delete at database!");
  }
}
