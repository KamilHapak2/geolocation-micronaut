package pl.com.britenet.interview.geolocation;

import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.configuration.mongo.reactive.MongoSettings;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.io.socket.SocketUtils;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CoordinatesControllerTest {

  private static EmbeddedServer server;
  private static CoordinatesControllerTestClient client;

  @BeforeAll
  static void setUp() {
    server =
            ApplicationContext.run(
                    EmbeddedServer.class,
                    Map.of(
                            MongoSettings.MONGODB_URI,
                            "mongodb://localhost:" + SocketUtils.findAvailableTcpPort()));
    client = server.getApplicationContext().getBean(CoordinatesControllerTestClient.class);
  }

  @AfterAll
  static void stopServer() {
    server.stop();
  }

  @AfterEach
  void tearDown() {
    ApplicationContext applicationContext = server.getApplicationContext();
    MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
    MongoConfiguration config = applicationContext.getBean(MongoConfiguration.class);
    Flowable.fromPublisher(mongoClient.getDatabase(config.getDatabase()).drop()).blockingFirst();
  }

  @Test
  void shouldAddCoordinates() {

    // given
    final String givenDeviceId = "12345";
    final long givenLatitude = -505430;
    final Double expectedLatitude = -50.5430;
    final long givenLongitude = 1423412;
    final Double expectedLongitude = 142.3412;
    final AddDeviceLocationRequest givenAddCoordinatesRequest =
            new AddDeviceLocationRequest(givenDeviceId, givenLongitude, givenLatitude);
    // when
    final DeviceLocation coordinates =
            client.addCoordinates(givenAddCoordinatesRequest).blockingGet();

    // then
    final TestSubscriber<DeviceLocation> testSubscriber = new TestSubscriber<>();
    client.getCoordinates(givenDeviceId).subscribe(testSubscriber);
    testSubscriber.awaitCount(1);
    testSubscriber.assertValue(v -> v.getDeviceId().equals(givenDeviceId));
    testSubscriber.assertValue(v -> v.getLatitude().getValue().equals(expectedLatitude));
    testSubscriber.assertValue(v -> v.getLongitude().getValue().equals(expectedLongitude));
    testSubscriber.assertValue(v -> v.getTimestamp() == coordinates.getTimestamp());
    testSubscriber.assertComplete();
  }
}
