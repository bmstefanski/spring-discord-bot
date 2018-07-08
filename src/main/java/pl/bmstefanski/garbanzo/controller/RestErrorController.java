package pl.bmstefanski.garbanzo.controller;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class RestErrorController implements ErrorController {

  private final ErrorAttributes errorAttributes;

  @Autowired
  public RestErrorController(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @GetMapping("/error")
  public ErrorJson error(WebRequest request, HttpServletResponse response) {
    return new ErrorJson(response.getStatus(), getErrorAttributes(request));
  }

  private Map<String, Object> getErrorAttributes(WebRequest request) {
    return errorAttributes.getErrorAttributes(request, false);
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  private static class ErrorJson {

    String timeStamp;
    Integer status;
    String error;
    String message;
    String path;

    ErrorJson(int status, Map<String, Object> errorAttributes) {
      this.timeStamp = String.valueOf(errorAttributes.get("timestamp"));
      this.status = status;
      this.error = String.valueOf(errorAttributes.get("error"));
      this.message = String.valueOf(errorAttributes.get("message"));
      this.path = String.valueOf(errorAttributes.get("path"));
    }

    public String getTimeStamp() {
      return this.timeStamp;
    }

    public Integer getStatus() {
      return this.status;
    }

    public String getError() {
      return this.error;
    }

    public String getMessage() {
      return this.message;
    }

    public String getPath() {
      return this.path;
    }

    public void setTimeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

    public void setError(String error) {
      this.error = error;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public void setPath(String path) {
      this.path = path;
    }

  }

}