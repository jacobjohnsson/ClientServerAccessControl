package communication;

import attribute.AccessControl;
import attribute.Subject;
import database.Database;

public class CreateRequest extends Request {
  public CreateRequest(Subject subject, AccessControl object, String description) {
    super(subject, object, description);
  }
  
  @Override
  public void execute(Database db) {
    // TODO
    System.out.println("Create new journal!");
  }
}
