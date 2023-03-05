package com.java.Test.Impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.Test.Services.FileService;

@Service
public class FileServiceImpl implements  FileService{

	@Override
	public List<File> getFiles(String directoryPath)
	{
		File directory=new File(directoryPath);
		
		File[]files=directory.listFiles();
		return Arrays.asList(files);
	}
}
