package DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import utility.FrameworkConstants;

public class DP {

	
	// First Approach is to make seprate Dataproviders and provide there names to TestCases
	 @DataProvider(name = "newPatientValidPayload")
	    public Object[] payload1() throws IOException, ParseException{
			HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(System.getProperty("user.dir")+FrameworkConstants.Valid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>() {});
		 return new Object[] {payloadData};
	    }
	 @DataProvider(name = "newPatientValidPayloads")
	    public Object[] payload2() throws IOException, ParseException{
			HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(System.getProperty("user.dir")+FrameworkConstants.Valid_Patient2_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>() {});
		 return new Object[] {payloadData};
	    } 
	 @DataProvider(name = "newPatientInalidPayload")
	    public Object[] payload3() throws IOException, ParseException{
			HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(System.getProperty("user.dir")+FrameworkConstants.Invalid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>() {});
		 return new Object[] {payloadData};
	    }
	 
	// Second Approach is to add them in to one Data Provider and pass that dataprovider to Test case.
	 
	 
	    public Object[] test_payload1() throws IOException, ParseException{
			HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(System.getProperty("user.dir")+FrameworkConstants.Valid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>() {});
		 return new Object[] {payloadData};
	    }
	
	    public Object[] test_payload2() throws IOException, ParseException{
			HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(System.getProperty("user.dir")+FrameworkConstants.Valid_Patient2_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>() {});
		 return new Object[] {payloadData};
	    } 
	 
	    public Object[] test_payload3() throws IOException, ParseException{
			HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(System.getProperty("user.dir")+FrameworkConstants.Invalid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>() {});
		 return new Object[] {payloadData};
	    }
	    
	    @DataProvider(name = "patientPayloadWithValidInvalidSenarios")
	    public Object[] dp() throws IOException, ParseException {
	    	
	    	List<Object[]> res = new ArrayList<Object[]>();
	    	res.add(test_payload1());
	    	res.add(test_payload2());
	    	res.add(test_payload3());
	    	return res.toArray(new Object[res.size()][]);
	     
	    }
	 
}
