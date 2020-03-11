package communication;

import attribute.AccessControl;
import attribute.ObjectControl;
import attribute.Subject;
import database.Database;

public class ReadRequest extends Request {
  public ReadRequest(Subject subject, AccessControl object) {
    super(subject, object, "read");
  }
  
  @Override
  public String execute(Database db) {
    if (object.hasAccess(subject, ObjectControl.AccessRight.READ)) {
      return db.read(object.getId());
    } else {
      return "No Access";
    }
    
  }
}
