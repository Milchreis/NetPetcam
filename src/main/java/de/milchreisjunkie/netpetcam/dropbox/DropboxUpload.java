package de.milchreisjunkie.netpetcam.dropbox;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.milchreisjunkie.netpetcam.Config;
import de.milchreisjunkie.netpetcam.interfaces.ICaptureHook;

public class DropboxUpload implements ICaptureHook {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("YYYY-MM-DD_HH-mm-ss");

	private DropboxManager dbxMan;
	private Config config;
	
	public DropboxUpload(DropboxManager dbxMan, Config config) {
		this.config = config;
		this.dbxMan = dbxMan;
	}
	
	@Override
	public void perform(File file) throws Exception {
		// Upload files
		dbxMan.uploadFile(file, generateName());

		// Get files and sort list
		List<String> files = dbxMan.getFileList();
		Collections.sort(files);

		// Delete oldest file, if maximum is reached
		if (files.size() > Integer.parseInt(config.getValue("maximages"))) {
			dbxMan.deleteFile(files.get(0));
		}
	}

	private static String generateName() {
		return SDF.format(new Date()) + ".jpg";
	}
}
