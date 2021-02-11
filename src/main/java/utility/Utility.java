package utility;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 public class Utility extends BaseTest{
	/*
	 * static Logger logger = Logger.getLogger(Utility.class.getName());
	 * 
	 * public static boolean verifyTextMatch(String actualText, String
	 * expectedText){ boolean flag = false; try {
	 * logger.info("Actual Text From Application Web UI --> :: " + actualText);
	 * logger.info("Expected Text From Application Web UI --> :: " + expectedText);
	 * 
	 * if(actualText.equals(expectedText)){
	 * logger.info("### VERIFICATION TEXT MATCHED !!!"); flag = true; }else{
	 * logger.error("### VERIFICATION TEXT DOES NOT MATCHED !!!"); }
	 * 
	 * }catch (Exception Ex){
	 * logger.error("Exception Occurred While Verifying The Text Match: " +
	 * Ex.getMessage()); } return flag; }
	 */
	
	public static String convertObjectArraytoJSONString(HashMap<String,Object> ObjectArray) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
	     String outputJsonString = objectMapper.writeValueAsString(ObjectArray);
	     return outputJsonString;
	}
}
