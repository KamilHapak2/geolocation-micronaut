package pl.com.britenet.interview.geolocation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeviceLocationConverterTest {

  private DeviceLocationConverter converter;
  private Clock clock;

  @BeforeEach
  void setUp() {
    clock = mock(Clock.class);
    converter = new DeviceLocationConverter(clock);
  }

  @Test
  @DisplayName("Should create from AddDeviceLocationRequest")
  void shouldCreateFromAddDeviceLocationRequest() {

    // given
    final long expectedTimestamp = 123123L;
    given(clock.millis()).willReturn(expectedTimestamp);
    final long givenLongitude = 1403320L;
    final long givenLatitude = 543215L;
    final AddDeviceLocationRequest givenRequest =
        new AddDeviceLocationRequest("1", givenLongitude, givenLatitude);
    final double expectedLongitude = 140.332;
    final double expectedLatitude = 54.3215;

    // when
    final DeviceLocation deviceLocation = converter.from(givenRequest);

    // then
    assertAll(
        () -> assertEquals(givenRequest.getDeviceId(), deviceLocation.getDeviceId()),
        () -> assertEquals(expectedLongitude, deviceLocation.getLongitude().getValue()),
        () -> assertEquals(expectedLatitude, deviceLocation.getLatitude().getValue()),
        () -> assertEquals(expectedTimestamp, deviceLocation.getTimestamp()));
  }

  @Test
  @DisplayName("Should create DeviceLocationDetails")
  void shouldCreateDeviceLocationDetails() {

    // given
    final long givenTimestamp = 123L;
    final String givenDeviceId = "19652532";
    final double givenLongitudeValue = 23.7731;
    final double givenLatitudeValue = 5.4912;

    final DeviceLocation deviceLocation =
        new DeviceLocation(
            givenTimestamp,
            givenDeviceId,
            Longitude.of(givenLongitudeValue),
            Latitude.of(givenLatitudeValue));

    // when
    final DeviceLocationDetails deviceLocationDetails = converter.from(deviceLocation);

    // then
    assertAll(
        () -> assertEquals(givenDeviceId, deviceLocationDetails.getDeviceId()),
        () -> assertEquals(givenTimestamp, deviceLocationDetails.getTimestamp()),
        () -> assertEquals(givenLatitudeValue, deviceLocationDetails.getLatitude()),
        () -> assertEquals(givenLongitudeValue, deviceLocationDetails.getLongitude()));
  }
}
