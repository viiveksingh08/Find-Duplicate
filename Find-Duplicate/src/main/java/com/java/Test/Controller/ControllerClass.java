package com.java.Test.Controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.Test.Services.DuplicateService;
import com.java.Test.Services.FileService;

@RestController
@RequestMapping("/get")
public class ControllerClass {

	private final FileService fileService;
	private final DuplicateService duplicateService;
	
	public ControllerClass(FileService fileService,DuplicateService duplicateService)
	{
		this.fileService=fileService;
		this.duplicateService=duplicateService;
	}
	
	//Api to get files from a folder....
	@GetMapping("/files")
	public List<String> get(@RequestParam String directoryPath)
	{
		List<File> files=fileService.getFiles(directoryPath);
		return files.stream().map(File::getName).collect(Collectors.toList());
	}
	
	//Api to get duplicates files from a folder....
	@GetMapping("/duplicates")
	public List<String> find(@RequestParam String directoryPath)
	{
		return duplicateService.findDuplicateFiles(directoryPath);
	}
}
