package co.paulozan.book.samples;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class DiscoveryClientSample implements CommandLineRunner {

  @Autowired
  private DiscoveryClient discoveryClient;

  @Override
  public void run(String... strings) throws Exception {
    System.out.println(discoveryClient.description());

    discoveryClient.getInstances("restaurant-service")
        .forEach((ServiceInstance serviceInstance) -> {
          System.out
              .println(new StringBuilder("Instance -->").append(serviceInstance.getServiceId())
                  .append("\nServer: ")
                  .append(serviceInstance.getHost())
                  .append(":")
                  .append(serviceInstance.getPort())
                  .append("\nURI: ").append(serviceInstance.getUri()).append("\n\n\n"));
        });
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(DiscoveryClientSample.class, args);
  }

}

