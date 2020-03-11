package communication;

import attribute.AccessControl;
import attribute.ObjectControl;
import attribute.Subject;
import database.Database;

public class DeleteRequest extends Request {
  public DeleteRequest(Subject subject, AccessControl object, String description) {
    super(subject, object, description);
  }
  
  @Override
  public String execute(Database db) {
    if (object.hasAccess(subject, ObjectControl.AccessRight.CREATE)) {
      return "Delete journal: \n" + object;
    } else {
      return "No access.";
    }
  }
}
