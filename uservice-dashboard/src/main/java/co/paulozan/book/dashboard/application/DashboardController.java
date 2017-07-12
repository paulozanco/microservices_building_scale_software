package co.paulozan.book.dashboard.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController extends SpringBootServletInitializer {

  @RequestMapping("/")
  public String home() {
    return "forward:/hystrix";
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(DashboardController.class).web(true);
  }

}
