package pl.com.britenet.interview.geolocation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeviceLocationServiceTest {

  private DeviceLocationService service;
  private DeviceLocationRepository repository;
  private ArgumentCaptor<DeviceLocation> locationArgumentCaptor;
  private Clock clock;

  @BeforeEach
  void setUp() {
    repository = mock(DeviceLocationRepository.class);
    clock = mock(Clock.class);
    service = new DeviceLocationService(repository, clock);
    locationArgumentCaptor = ArgumentCaptor.forClass(DeviceLocation.class);
  }

  @Test
  void shouldSaveDeviceLocationWithCorrectValuesAndTimestamp() {

    // given
    final AddDeviceLocationRequest addLocationRequest =
            new AddDeviceLocationRequest("1", 78000L, 350000L);
    final Double expectedLongitude = 7.8;
    final Double expectedLatitude = 35.0;
    final long expectedTimestamp = 123L;
    given(clock.millis()).willReturn(expectedTimestamp);

    // when
    service.save(addLocationRequest);

    // then
    verify(repository).save(locationArgumentCaptor.capture());
    final DeviceLocation deviceLocation = locationArgumentCaptor.getValue();
    assertEquals(expectedTimestamp, deviceLocation.getTimestamp());
    assertEquals(expectedLatitude, deviceLocation.getLatitude().getValue());
    assertEquals(expectedLongitude, deviceLocation.getLongitude().getValue());
  }
}
