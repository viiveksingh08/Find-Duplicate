package com.java.Test.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DuplicateService {

	List<String> findDuplicateFiles(String directoryPath);
}
