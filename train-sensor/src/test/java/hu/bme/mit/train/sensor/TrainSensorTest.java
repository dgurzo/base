package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainSensor sensor;
    TrainController controller;
    TrainUser user;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void SpeedLimitOver500() {
        sensor.overrideSpeedLimit(501);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitUnder0() {
        sensor.overrideSpeedLimit(-1);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitDrop() {
        sensor.overrideSpeedLimit(100);
        sensor.overrideSpeedLimit(10);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitGood() {
        sensor.overrideSpeedLimit(50);
        verify(user, times(0)).setAlarmState(true);
    }
}
