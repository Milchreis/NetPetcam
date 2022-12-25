package de.milchreisjunkie.netpetcam.interfaces;

public interface ICaptureDevice {

	void setup(Object options) throws Exception;
	void capture() throws Exception;
	
}
