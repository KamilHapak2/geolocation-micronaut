package pl.com.britenet.interview.geolocation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Longitude object creation tests")
class LongitudeTest {

  private static Stream<Double> getInvalidLongitude() {
    return Stream.of(180.1, -180.0001, -181.0, 180.0001);
  }

  private static Stream<Double> getValidLongitude() {
    return Stream.of(180.0, -179.9999, -180.0, 0.0);
  }

  @ParameterizedTest
  @DisplayName("Should fail due to invalid longitude")
  @MethodSource("getInvalidLongitude")
  void shouldFailDueToInvalidLongitude(Double value) {

    // then
    assertThrows(
        IllegalArgumentException.class,
        // when
        () -> Longitude.of(value),
        String.format("%s is invalid, should fail", value));
  }

  @ParameterizedTest
  @DisplayName("Should create Longitude")
  @MethodSource("getValidLongitude")
  void shouldCreateLongitude(Double value) {

    // then
    assertDoesNotThrow(
        // when
        () -> Longitude.of(value), String.format("%s is correct, should create object", value));
  }
}
