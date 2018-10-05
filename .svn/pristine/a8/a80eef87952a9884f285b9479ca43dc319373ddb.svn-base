package de.milchreisjunkie.netpetcam;

import java.io.File;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

import de.milchreisjunkie.netpetcam.interfaces.ICaptureDevice;

public class WebcamDevice implements ICaptureDevice {

	private Webcam webcam;
	private String path;

	@Override
	public void setup(Object options) throws Exception {
		webcam = Webcam.getDefault();
		path = options.toString();
	}

	@Override
	public void capture() throws Exception {
		webcam.open();
		ImageIO.write(webcam.getImage(), "jpg", new File(path, "out.jpg"));
		webcam.close();
	}

}
