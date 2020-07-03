package helpers;

import javax.servlet.http.HttpServletRequest;

public class Validator {

  private static boolean isValid(HttpServletRequest request, String fieldName) {
    String fieldValue = request.getParameter(fieldName);

    return fieldValue != null && !fieldValue.trim().isEmpty();
  }


  public static boolean areValidParams(HttpServletRequest request, String[] fields) {
    for (String field: fields) {
      if(!isValid(request, field)) {
        return false;
      }
    }

    return true;
  }

}
