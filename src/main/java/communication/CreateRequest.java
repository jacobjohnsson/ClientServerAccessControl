package communication;

import attribute.AccessControl;

import attribute.ObjectControl;
import attribute.Subject;
import database.Database;

public class CreateRequest extends Request {
  public CreateRequest(Subject subject, AccessControl object, String description) {
    super(subject, object, description);
  }
  
  @Override
  public String execute(Database db) {
    if (object.hasAccess(subject, ObjectControl.AccessRight.CREATE)) {
      return "Create new journal!";
    } else {
      return "No access.";
    }
  }
}
