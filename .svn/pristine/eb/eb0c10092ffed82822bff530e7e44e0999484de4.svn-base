package de.milchreisjunkie.netpetcam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

public class DropboxManager {

	private DbxRequestConfig config;
	private DbxClientV2 client;
	
	public DropboxManager(String dropboxAccessToken) {
		config = DbxRequestConfig.newBuilder("npetcam").build();
		client = new DbxClientV2(config, dropboxAccessToken);
	}
	
	public List<String> getFileList() throws ListFolderErrorException, DbxException {
		List<String> files = new ArrayList<>();
		
		ListFolderResult result = client.files().listFolder("");
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				files.add(metadata.getName());
			}

			if (!result.getHasMore()) {
				break;
			}

			result = client.files().listFolderContinue(result.getCursor());
		}
		return files;
	}
	
	public Metadata deleteFile(String dbxFile) throws DeleteErrorException, DbxException {
		return client.files().delete("/"+dbxFile);
	}
	
	
	public FileMetadata uploadFile(File file, String targetname) throws Exception {
		try (InputStream in = new FileInputStream(file)) {
			return client.files().uploadBuilder("/"+targetname).uploadAndFinish(in);
		}
	}
	
	public FileMetadata uploadFile(File file) throws Exception {
		return uploadFile(file, file.getName());
	}
}
