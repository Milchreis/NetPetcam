package de.milchreisjunkie.netpetcam;

import com.hopding.jrpicam.RPiCamera;

import de.milchreisjunkie.netpetcam.interfaces.ICaptureDevice;

public class RpiCam implements ICaptureDevice {

	private RPiCamera piCamera;
	
	@Override
	public void setup(Object options) throws Exception {
		piCamera = new RPiCamera(options.toString());
		piCamera.setWidth(800); 
		piCamera.setHeight(600);
		piCamera.setTimeout(2000);
		piCamera.setDateTimeOff();
	}

	@Override
	public void capture() throws Exception {
		piCamera.takeStill("out.jpg");
	}
}
