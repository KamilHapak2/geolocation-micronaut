package pl.com.britenet.interview.geolocation;

import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.configuration.mongo.reactive.MongoSettings;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("DeviceLocationController - Integration tests")
@Testcontainers
public class DeviceLocationControllerTest {

  @Container
  public static GenericContainer mongoContainer =
      new GenericContainer<>("mongo:4.2.0").withExposedPorts(27017);

  private static EmbeddedServer server;
  private static CoordinatesControllerTestClient client;

  @AfterAll
  static void stopServer() {
    server.stop();
  }

  @BeforeAll
  static void setUp() {
    server =
        ApplicationContext.run(
            EmbeddedServer.class,
            Map.of(
                MongoSettings.MONGODB_URI,
                "mongodb://localhost:" + mongoContainer.getFirstMappedPort()));
    client = server.getApplicationContext().getBean(CoordinatesControllerTestClient.class);
  }

  @BeforeEach
  void prepareDatabase() {
    ApplicationContext applicationContext = server.getApplicationContext();
    MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
    MongoConfiguration config = applicationContext.getBean(MongoConfiguration.class);
    Flowable.fromPublisher(mongoClient.getDatabase(config.getDatabase()).drop()).blockingFirst();
  }

  @Test
  @DisplayName("Should add device location")
  void shouldAddDeviceLocation() {

    // given
    final String givenDeviceId = "12345";
    final long givenLatitude = -505430;
    final double expectedLatitude = -50.5430;
    final long givenLongitude = 1423412;
    final double expectedLongitude = 142.3412;
    final AddDeviceLocationRequest givenAddCoordinatesRequest =
        new AddDeviceLocationRequest(givenDeviceId, givenLongitude, givenLatitude);

    // when
    final DeviceLocationDetails coordinates =
        client.addCoordinates(givenAddCoordinatesRequest).blockingGet();

    // then
    final TestSubscriber<DeviceLocationDetails> testSubscriber = new TestSubscriber<>();
    client.getCoordinates(givenDeviceId).subscribe(testSubscriber);
    assertAll(
        () -> testSubscriber.awaitCount(1),
        () -> testSubscriber.assertValue(v -> v.getDeviceId().equals(givenDeviceId)),
        () -> testSubscriber.assertValue(v -> v.getLatitude() == expectedLatitude),
        () -> testSubscriber.assertValue(v -> v.getLongitude() == expectedLongitude),
        () -> testSubscriber.assertValue(v -> v.getTimestamp() == coordinates.getTimestamp()),
        testSubscriber::assertComplete);
  }
}
