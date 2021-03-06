package communication;

import attribute.AccessControl;
import attribute.Subject;
import database.Database;

public class InvalidRequest extends Request {
  public InvalidRequest(Subject subject, AccessControl object) {
    super(subject, object, "INVALID");
  }
  
  @Override
  public String execute(Database db) {
    return "Invalid request";
  }
}

