package com.example.multifileupload.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.multifileupload.service.MultipleFilesUploadService;
import com.example.multifileupload.util.FileUploadResponse;
import com.example.multifileupload.util.FileUtil;

@RestController
@RequestMapping("/api/multifile")
public class MultipleFilesUploadController {

	@Autowired
	MultipleFilesUploadService service;

	@PostMapping("/upload")
	public ResponseEntity<FileUploadResponse> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new FileUploadResponse("Files uploaded successfully: " + service.uploadFiles(files)));
	}

	@GetMapping("/files")
	public ResponseEntity<String[]> getListFiles() {
		return ResponseEntity.status(HttpStatus.OK).body(new File(FileUtil.folderPath).list());
	}

}
