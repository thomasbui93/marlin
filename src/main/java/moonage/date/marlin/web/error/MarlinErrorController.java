package moonage.date.marlin.web.error;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MarlinErrorController implements ErrorController {

  @RequestMapping("/error")
  public ResponseEntity<HashMap<String, String>> handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    HashMap<String, String> response = new HashMap<String, String>();
    response.put("message", "An error has occured!");
    if (status != null) {
      return ResponseEntity
        .status(HttpStatus.valueOf(Integer.valueOf(status.toString())))
        .body(response);
    }
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(response);
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}