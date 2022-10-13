package apiConfigs;

public class API_Endpoint {

	public static final class Endpoints
	{
		public static final String postCreatePatientURI="api/patient/v1/patients";
		public static final String postSendOTPPath="api/v1/patient/send-otp";
		public static final String postVerifyOTP="api/v1/patient/verify-otp";
		public static final String getPatientProfile="api/v1/patient/profile";
		public static final String getPatientsProfile="api/patient/v1/patients/profile";
		public static final String getPatientDetails="api/patient/v1/patient_accounts/8860005106/list";
		public static final String postPatientRegister="api/v1/patient/register";
		public static final String getPatientPrograms="api/v1/patient/programs";
		public static final String getStates="api/v1/location/state";
		public static final String getCities="api/v1/location/PB/city";
		public static final String getCareTakerRelationList="api/v1/care-taker/relations";
		public static final String postUploadProfilePicture="api/v1/patient/profile/picture";
		public static final String postPatientLogin="api/v1/patient/login";
		public static final String postPatientCompleteProfile="api/v1/patient/profile/complete";
		public static final String postLearningContent="api/v1/patient/learn/track";
		public static final String getEnrollmentSection="api/v1/patient/program-enrollment/section";
		public static final String getEnrollmentSectionQuestions="api/v1/patient/program-enrollment/{section_id}/questions";
		public static final String getEnrollmentDismissCard="api/v1/patient/program-enrollment/dismiss";
		public static final String postEnrollmentSectionSubmitAnswer="api/v1/patient/program-enrollment/{section_id}/submit-answer";
		public static final String KPPGetHistory ="api/v1/patient/kpp/history/{module_id}?page=1";
		public static final String KPPGetQuestions ="api/v1/patient/kpp/{module_id}/questions";
		public static final String postUpdateTracking="api/v1/patient/kpp/{module_id}/update/{track_id}";
		public static final String postCareLead="api/v1/patient/care/lead";
		public static final String postSaveTracking="api/v1/patient/kpp/{track_id}/track";
		public static final String getHome="api/v1/patient/home";
		public static final String getSplashScreen="api/v1/patient/start";
		
		
		
		
	}
}
