package com.java.Test.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.Test.Services.DuplicateService;

@Service
public class DuplicateServiecImpl implements DuplicateService{
	
	@Override
	public List<String> findDuplicateFiles(String directoryPath){
		Map<String,List<String>> cont=new HashMap<>();
		List<String> duplicates=new ArrayList<>();
		
		
		for(File file:new File(directoryPath).listFiles()) {
			 
			if(file.isFile()) {
				try {
					//calling getContent method....
					String content=getContent(file);
					
					if(cont.containsKey(content)) {
						for(String duplicate: cont.get(content)) {
							String duplicateName=new File(duplicate).getName();
							if(!duplicates.contains(duplicateName)) {
								duplicates.add(duplicateName);
							}
						}
						String fileName=file.getName();
						if(!duplicates.contains(fileName)) {
							duplicates.add(fileName);
						}
					}
					
					else
					{
						cont.computeIfAbsent(content,k->new ArrayList<>()).add(file.getAbsolutePath());
					}
				}catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return duplicates;
	}
	
	private String getContent(File file) throws IOException{
		try(BufferedReader reader=new BufferedReader(new FileReader(file))){
			StringBuilder cb=new StringBuilder();
			String line;
			
			while((line=reader.readLine())!=null)
			{
				cb.append(line);
			}
			return cb.toString();
		}
	}
}
