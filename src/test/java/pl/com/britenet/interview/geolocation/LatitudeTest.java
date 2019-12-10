package pl.com.britenet.interview.geolocation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LatitudeTest {

  private static Stream<Double> getInvalidLatitude() {
    return Stream.of(90.1, -90.1, -91.0, 91.0);
  }

  private static Stream<Double> getValidLatitude() {
    return Stream.of(90.0, -89.9999, -89.9999, 0.0, 90.0);
  }

  @ParameterizedTest
  @MethodSource("getInvalidLatitude")
  void shouldFailDueToInvalidLatitude(Double value) {

    // then
    assertThrows(
        IllegalArgumentException.class,
        // when
        () -> Latitude.of(value));
  }

  @ParameterizedTest
  @MethodSource("getValidLatitude")
  void shouldCreateLatitude(Double value) {

    // then
    assertDoesNotThrow(
        // when
        () -> Latitude.of(value));
  }
}
