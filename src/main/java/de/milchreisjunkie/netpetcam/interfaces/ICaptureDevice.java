package de.milchreisjunkie.netpetcam.interfaces;

public interface ICaptureDevice {

	public void setup(Object options) throws Exception;
	public void capture() throws Exception;
}
