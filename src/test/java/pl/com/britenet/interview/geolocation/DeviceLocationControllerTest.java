package pl.com.britenet.interview.geolocation;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class DeviceLocationControllerTest {

  @Container
  public static GenericContainer mongoContainer =
      new GenericContainer<>("mongo:4.2.0");

}
