package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
	
	private TrainController tc;
	private TrainUser tu;
	private TrainSensorImpl ts;

	
    @Before
    public void before() {
        // TODO Add initializations
    	tc= mock(TrainController.class);
    	tu= mock(TrainUser.class);
    	ts= new TrainSensorImpl(tc,tu);
    	//ts.overrideSpeedLimit(100);
    }

    @Test
    public void negativeSpeedLimit() {
    	ts.overrideSpeedLimit(-1);
    	//Assert.assertTrue(tu.getAlarmState());
    	verify(tu,times(1)).setAlarmState(true);;
    	
    }
    @Test
    public void zeroSpeedLimit() {
    	ts.overrideSpeedLimit(0);
    	//Assert.assertFalse(tu.getAlarmState());
    	verify(tu,times(1)).setAlarmState(false);;
    }
    @Test
    public void over500SpeedLimit() {
    	ts.overrideSpeedLimit(501);
    	//Assert.assertTrue(tu.getAlarmState());
    	verify(tu,times(1)).setAlarmState(true);;
    }
    @Test
    public void refSpeedLimit() {
    	when(tc.getReferenceSpeed()).thenReturn(500);
    	ts.overrideSpeedLimit(10);
    	//Assert.assertTrue(tu.getAlarmState());
    	verify(tu,times(1)).setAlarmState(true);;
    }
}
