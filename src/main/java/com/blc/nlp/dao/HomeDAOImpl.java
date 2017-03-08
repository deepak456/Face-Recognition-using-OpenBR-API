package com.blc.nlp.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;



@Repository
public class HomeDAOImpl implements HomeDAO {
	
	@Value("${destinationPath}")
	private String destinationPath;
	@Value("${csvFile}")
	private String csvFile;
	

	@Override
	public Map<Object,Object> searchFile(MultipartFile multipartFile) throws Exception {
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
		byte[] bytes = multipartFile.getBytes();
		String storagePath ="Temp";
		File file = new File(storagePath);
		if(!file.exists()){
			file.mkdirs();
		}
        Path path = Paths.get(file+File.separator+multipartFile.getOriginalFilename());
        String filePath=file+File.separator+path.getFileName();
        if(path!=null && multipartFile.getOriginalFilename()!= null){
        Files.write(path, bytes);
        }
        String cmd ="br -algorithm FaceRecognition -compare "+filePath+" "+destinationPath+" "+csvFile+"";
        System.out.println("comd :"+cmd);
        StringBuffer output = new StringBuffer();
        Runtime run = Runtime. getRuntime();
        Process process = run. exec(cmd);
        int result =process.waitFor();
        System.out.println("result : "+result);
        if(result<1){
        Files.deleteIfExists(path);}
        if(csvFile !=null){
        	map = readCSV(csvFile);       
        }
		}catch(Exception ex){
			ex.getMessage();
		}
		return map;
	}
	
	
	public static Map<Object,Object> readCSV(String filePath) throws FileNotFoundException, IOException {
		System.out.println("running ");
        Map<Object,Object>map = new HashMap<Object,Object>();
        try{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine(); // Reading header, Ignoring
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            if(isNumeric(fields[1])){
            	double d = Double.parseDouble(fields[1]);
            	System.out.println(d +"values");
            	if(d>1){
            		 map.put(fields[0].substring(fields[0].lastIndexOf("Image")),fields[1]);
            	}
            }
            /*if (v1.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            		System.out.println("Is a number" +v1);
            } else {
            	System.out.println("Is not a number"+v1);
            }*/
            //int values = Integer.parseInt(fields[1]);
           //System.out.println(values  +"valuesss");
           
        }
        br.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }

        return map;
    }
	
	public static boolean isNumeric(String str)
	  {
	    try
	    {
	    	double d = Double.parseDouble(str);
	      
	    }
	    catch(NumberFormatException nfe)
	    {
	    	nfe.getMessage();
	      return false;
	    }
	    return true;
	  }
	
}