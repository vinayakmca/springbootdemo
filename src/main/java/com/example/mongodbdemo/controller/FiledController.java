package com.example.mongodbdemo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(allowedHeaders = "*",allowCredentials ="false")
@RestController
@RequestMapping("/demo")
public class FiledController {
	@GetMapping("/ok")
	public String getDemo() {
		return "ok";
	}
	
	// @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PostMapping("/upload")  
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
	 
	        File uploadedFile = new File("C:\\uploadDir", file.getOriginalFilename());
	 
	        try {
	            uploadedFile.createNewFile();
	            FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
	            fileOutputStream.write(file.getBytes());
	            fileOutputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return new ResponseEntity<Object>("file Uplaoded succesfully", HttpStatus.OK);
	    }
	     
}
