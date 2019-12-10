package pl.com.britenet.interview.geolocation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LongitudeTest {

  private static Stream<Double> getInvalidLongitude() {
    return Stream.of(180.1, -180.1, -181.0, 181.0);
  }

  @ParameterizedTest
  @MethodSource("getInvalidLongitude")
  void shouldFailDueToInvalidLongitude(Double value) {

    // then
    assertThrows(
            IllegalArgumentException.class,
            // when
            () -> Longitude.of(value));
  }
}
