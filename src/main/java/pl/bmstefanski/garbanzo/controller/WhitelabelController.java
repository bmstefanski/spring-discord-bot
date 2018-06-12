package pl.bmstefanski.garbanzo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhitelabelController implements ErrorController {

  private static final String PATH = "/error";

  @RequestMapping(PATH)
  public String error() {
    return "Invalid end point!";
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }

}
