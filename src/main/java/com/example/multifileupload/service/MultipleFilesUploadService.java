package com.example.multifileupload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techgeeknext.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MultipleFilesUploadService {

	public List<String> uploadFiles(MultipartFile[] files) {
		List<String> fileNames = new ArrayList<>();
		try {
			createDirIfNotExist();

			Arrays.asList(files).stream().forEach(file -> {
				byte[] bytes = new byte[0];
				try {
					bytes = file.getBytes();
					Files.write(Paths.get(FileUtil.folderPath + file.getOriginalFilename()), bytes);
					fileNames.add(file.getOriginalFilename());
				} catch (IOException e) {

				}
			});

		} catch (Exception ex) {
			log.error("Exception Occurred -->>", ex);
		}
		return fileNames;
	}

	private void createDirIfNotExist() {
		File directory = new File(FileUtil.folderPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

}
