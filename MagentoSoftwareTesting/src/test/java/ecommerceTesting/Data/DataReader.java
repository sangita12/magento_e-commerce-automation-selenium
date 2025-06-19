package ecommerceTesting.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public DataReader() {
		
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// 1. Read the contents of a JSON file (ShoppingData.json) as a String in Java,
		// using Apache Commons IO's FileUtils.

		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

		// 2. Convert String to HashMap 
		
		// ObjectMapper is used for converting between Java objects and JSON
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>>  data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){
			
		} );
		return data;
		
	}

}
