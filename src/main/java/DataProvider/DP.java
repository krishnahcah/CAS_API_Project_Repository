package DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DP {
	String filePath=System.getProperty("user.dir")+"/src/test/java/Requests/";
	
	@DataProvider(name = "patientLoginWithValidPayload")
	public Object[] payload1() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"patientLoginValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "patientLoginWithInvalidPayload_Missing_ProgramID")
	public Object[] payload2() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"patientLoginWithInvalidPayload_Missing_ProgramID.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "patientLoginWithInvalidPayload_Missing_DeviceInfo")
	public Object[] payload3() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"patientLoginWithInvalidPayload_Missing_DeviceInfo.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "patientLoginWithInvalidPayload_Invalid_ProgramID")
	public Object[] payload4() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"patientLoginWithInvalidPayload_Invalid_ProgramID.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	
	@DataProvider(name = "patientCompleteProfile_Valid_Auth_Valid_Fields")
	public Object[] payload5() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"patientCompleteProfile_Valid_Auth_Valid_Fields.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "patientCompleteProfile_Valid_Auth_Invalid_Fields")
	public Object[] payload6() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"patientCompleteProfile_Valid_Auth_Invalid_Fields.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "learningContentValidPayload")
	public Object[] payload7() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"learningContentValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	
	@DataProvider(name = "learningContentEmptyPayload")
	public Object[] payload8() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"learningContentEmptyPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "EnrollmentSubmitAnswersWithValidPayload")
	public Object[] payload9() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"enrollmentSubmitAnswersWithValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "EnrollmentSubmitAnswersWithEmptyPayload")
	public Object[] payload10() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"enrollmentSubmitAnswersWithEmptyPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "Care_Lead_WithValidPayload")
	public Object[] payload11() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"Care_Lead_WithValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	@DataProvider(name = "Care_Lead_WithEmptyPayload")
	public Object[] payload12() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"Care_Lead_WithEmptyPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	/*
	 * @DataProvider(name = "newPatientValidPayloads") public Object[] payload2()
	 * throws IOException, ParseException{ HashMap<String,Object> payloadData=new
	 * ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Valid_Patient2_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>()
	 * {}); return new Object[] {payloadData}; }
	 * 
	 * @DataProvider(name = "newPatientInalidPayload") public Object[] payload3()
	 * throws IOException, ParseException{ HashMap<String,Object> payloadData=new
	 * ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Invalid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>()
	 * {}); return new Object[] {payloadData}; }
	 */
	 
	// Second Approach is to add them in to one Data Provider and pass that dataprovider to Test case.
	 
	 
	/*
	 * public Object[] test_payload1() throws IOException, ParseException{
	 * HashMap<String,Object> payloadData=new ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Valid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>()
	 * {}); return new Object[] {payloadData}; }
	 * 
	 * public Object[] test_payload2() throws IOException, ParseException{
	 * HashMap<String,Object> payloadData=new ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Valid_Patient2_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>()
	 * {}); return new Object[] {payloadData}; }
	 * 
	 * public Object[] test_payload3() throws IOException, ParseException{
	 * HashMap<String,Object> payloadData=new ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Invalid_Patient_DATA_FILE_PATH), new TypeReference<HashMap<String,Object>>()
	 * {}); return new Object[] {payloadData}; }
	 * 
	 * @DataProvider(name = "patientPayloadWithValidInvalidSenarios") public
	 * Object[] dp() throws IOException, ParseException {
	 * 
	 * List<Object[]> res = new ArrayList<Object[]>(); res.add(test_payload1());
	 * res.add(test_payload2()); res.add(test_payload3());
	 * 
	 * return res.toArray(new Object[res.size()][]);
	 * 
	 * }
	 * 
	 * @DataProvider(name = "patient_Registration_All_Valid_Payload") public
	 * Object[] patient_Registration_All_Valid_Payload() throws IOException,
	 * ParseException{ HashMap<String,Object> payloadData=new
	 * ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Patient_Registration_Valid_All_Fields_DATA_FILE_PATH), new
	 * TypeReference<HashMap<String,Object>>() {}); return new Object[]
	 * {payloadData}; }
	 * 
	 * @DataProvider(name = "get_All_States_Valid_Payload") public Object[]
	 * get_All_States_Valid_Payload() throws IOException, ParseException{
	 * HashMap<String,Object> payloadData=new ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.Valid_States_File_Path
	 * ), new TypeReference<HashMap<String,Object>>() {}); return new Object[]
	 * {payloadData}; }
	 * 
	 * @DataProvider(name = "get_All_States_Invalid_Payload") public Object[]
	 * get_All_States_Invalid_Payload() throws IOException, ParseException{
	 * HashMap<String,Object> payloadData=new ObjectMapper().readValue(new
	 * File(System.getProperty("user.dir")+FrameworkConstants.
	 * Invalid_States_File_Path), new TypeReference<HashMap<String,Object>>() {});
	 * return new Object[] {payloadData}; }
	 */
	 
}
