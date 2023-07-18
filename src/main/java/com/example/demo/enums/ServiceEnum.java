package com.example.demo.enums;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author fazal
 *
 */

public enum ServiceEnum {

	AUTHENDICATION, NOTARY_USER, USER_ROLES, SOURCE_TYPE, REQUEST, CLASSIFICATION, MAINSERVICE, SUBSERVICE,
	ROLE_SCREEN_MAP, USERS, OFFICE, CORPORATION, GOVERNMENT, EMPLOYEEUSERUSERSTATUS, ATTACHMENTCONFIGURATION,
	BUSINESSPARTNER, APPLICANT, STATICS, ENTITYUSER, BUSINESSOBJECT, PERSONAL, AGENT, CUSTODIAN, GUARDIAN, CURATOR,
	LAWYER, INMATE;

	public enum PersonCategoryEnums {
		REPRESENTATIVE("Representative"), PARTNER("Partner"), CORPORATIONMANAGER("Corporation manager"),
		DEPUTY_DIRECTOR_OF_THE_CORPORATION("Deputy Director of the corporation"), CHAIRMAN("Chairman"),
		VICE_CHAIRMAN_BOD("Vice Chairman of the Board of Directors"), EXECTUTIVE_DIRECTOR("Executive Director"),
		MEMBER_OF_THE_BOARD_OF_DIRECTOR("Member of the Board of Directors"), ACTING_DIRECTOR("Acting Director"),
		CORPORATE_OWNER("Corporation owner"),
		MANAGER_OF_A_FOREIGN_CORPORATION_BRANCH("Manager of a foreign corporation branch"),
		CHAIRMAN_OF_THE_BOARD_OF_DIRECTORS("Chairman of the Board of Directors"),
		DEPUTY_BOARD_OF_DIRECTORS("Deputy Board of Directors"), INDIVIDUAL("Individual"), CORPORATION("Corporation");

		private String symbolicName;

		PersonCategoryEnums(String symbolicName) {
			this.symbolicName = symbolicName;
		}

		public String getSymbolicName() {
			return symbolicName;
		}
	}

	public enum Authentication_Error_Codes {
		BAD_CREDENTAIALS("2001"), USERNAME_NOT_FOUND("2002"), INVALID_KEY_EXCEPTION("2003"),
		ILLEGAL_ARGUMENT_EXCEPTION("2004"), INVALID_CREDENTIALS("2005"), TOKEN_EXPIRED_EXCEPTION("403"),
		SIGNATURE_EXCEPTION("2007"), EXPIRED_JWT_EXCEPTION("2008"), MALFORMED_JWT_EXCEPTION("2009"),
		UNSUPPORTED_JWT_EXCEPTION("2010"), AUTH_TOKEN_EXPIRED_EXCEPTION("2011"), DISABLED_EXCEPTION("2012"),
		AUTHENTICATION_CREDENTIALS_NOTFOUND_EXCEPTION("2013"), INTERNAL_AUTHENTICATION_SERVICE_EXCEPTION("2014"),
		EXCEPTION("5000");

		private String errorCode;

		private Authentication_Error_Codes(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorCode() {
			return errorCode;
		}

	}

	public enum ResourceNotFoundErrorCodes {
		SOURCE_TYPE_NOT_FOUND("4003");

		private String errorCode;

		private ResourceNotFoundErrorCodes(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorCode() {
			return errorCode;
		}

	}

	public enum ApplicantCategories {

		INDIVIDUAL(1), CORPORATION(2), GOVERNMENT_ENTITY(3), PRINTING_OFFICE(4), LAWYER_OFFICE(5);

		private int symbolicName;

		ApplicantCategories(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum AttendanceMethod {

		PHYSICAL(1), CONFERENCE_CALL(2);

		private int symbolicName;

		AttendanceMethod(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum Roles {

		ADMIN(1), NOTARY_EMPLOYEE_ESERVICES(2), ARAMEX(3), HEAD_OF_ATTESTATION_DEPARTMENT(4),
		HEAD_OF_FIRST_INSTANT_COURT(5), TRANSLATION_DEPARTMENT(6), NOTARY_EMPLOYEE_QSERVICES(7);

		private int symbolicName;

		Roles(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum SignatureMethodEnum {

		MANUAL(1), ELECTRONIC(2), DIGITAL(3);

		private long symbolicName;

		SignatureMethodEnum(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public long getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum PartyLegalStatus {

		PERSONAL("Personal", "1"), AGENT("Agent", "2"), CUSTODIAN("Custodian", "3"), GUARDIAN("Guardian", "4"),
		CURATOR("Curator", "5"), LAWYER("Lawyer", "6"),
		INMATE_OF_PENAL_CORR_INSTITUTION("Inmate of the Penal And Correctional Institution", "7"),
		REPRESENTATIVE("Representative", "8");

		private String symbolicName;
		private int symbolicValue;

		private final List<String> values;

		public String getSymbolicName() {
			return symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicValue;
		}

		PartyLegalStatus(String... values) {
			this.values = Arrays.asList(values);
			this.symbolicName = this.values.get(0);
			this.symbolicValue = Integer.parseInt(this.values.get(1));
		}

		public List<String> getValues() {
			return values;
		}

	}

	public enum SourceType {

		ADMIN(3), EXTERNAL(2), INTERNAL(1);

		private int sourceType;

		SourceType(int sourceType) {
			this.sourceType = sourceType;
		}

		public int getSourceType() {
			return sourceType;
		}

		public int getSymbolicValue() {
			return sourceType;
		}

	}

	public enum RequestStatus {

		DRAFT(1), PENDING_FOR_PAYMENT(2), UNDER_REVIEW(3), RETURNED(4), PENDING_FOR_APPLICANT_SCHEDULING(5),
		WAITING_FOR_TRANSLATION(6), WAITING_FOR_PARTIES_SIGNATURE(7), WAITING_FOR_ARCHIVING(8),
		WAITING_FOR_ATTESTATION(9), WAITING_FOR_CALL_NOTIFICATION(10), SENT_TO_ARAMEX(11), CANCELLED(12),
		SENT_TO_ANNOUNCEMENT_DEPARTMENT(13), COMPLETED(14), REJECTED(15), SOFT_DELETE(16),
		WAITING_IN_PRINTINGOFFICE(23),UNKNOWN(31), UNSUCCESSFUL(30), WAITING_IN_ELECTRONICS(22),
		WAITING_FOR_PUBLICATION(27);


		private int symbolicName;

		RequestStatus(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum BusinessFieldLookupTable {
		EMIRATES("EMIRATES"), PROPERTYTYPE("PROPERTY_TYPE"), COUNTRY("COUNTRY"), SHARESTYPE("SHARES_TYPE"),
		PARTY("PARTY"), CONTRACTTYPE("CONTRACT_TYPE"), ANNUAL_FEE_AED("ANNUAL_FEE_AED"),
		ATTORNEY_SOURCE("ATTORNEY_SOURCE"), CHURCH_NAME("CHURCH_NAME"),
		CONTRACT_ADDENDUM_TYPE("CONTRACT_ADDENDUM_TYPE"), POWER_ATTORNEY_ISSUED("POWER_ATTORNEY_ISSUED"),
		PAYMENT_TYPE("PAYMENT_TYPE"), FINANCIAL_TYPE("FINANCIAL_TYPE");

		private String symbolicName;

		BusinessFieldLookupTable(String symbolicName) {
			this.symbolicName = symbolicName;
		}

		public String getSymbolicName() {
			return symbolicName;
		}
	}

	public enum NotaryUserStatusEnum {

		DEACTIVATE(1), ACTIVATE(2), RESIGNED(3);

		private int symbolicName;

		NotaryUserStatusEnum(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum NotaryUserResigned {

		RESIGNED(1), NOT_RESIGNED(0);

		private int symbolicName;

		NotaryUserResigned(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum RequestStatusCodes {

		CREATED(201), DELETE(204), UPDATED(200), SUCCESS(200), FAILED(400), REGISTERED(200), SUBMITTED(200);

		private int symbolicName;

		RequestStatusCodes(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum ResponseMessage {
		CREATED, UPDATED, SUCCESS, DELETED, REGISTERED, SUBMITTED;
	}

	public enum FileExtensionTypes {
		PDF, JPG, JPEG, PNG;
	}

	public enum RequestTranslateStatus {

		DRAFT("Pending"), FINISH("Finish");

		private String symbolicName;

		RequestTranslateStatus(String symbolicName) {
			this.symbolicName = symbolicName;
		}

		public String getSymbolicValue() {
			return symbolicName;
		}
	}

	public enum SubserviceFeeType {

		FIXED("Fixed"), VARIANT("Variant");

		private String symbolicName;

		SubserviceFeeType(String symbolicName) {
			this.symbolicName = symbolicName;
		}

		public String getSymbolicName() {
			return symbolicName;
		}
	}

	public enum EntityUserStatus {

		ACTIVE(1), INACTIVE(2);

		private int symbolicValue;

		EntityUserStatus(int symbolicName) {
			this.symbolicValue = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicValue;
		}
	}

	public enum EntityStatus {

		ACTIVE("Active",1), INACTIVE("Inactive",2);

		private String status;
		private long value;
		

		EntityStatus(String status, long value) {
			this.status = status;
			this.value = value;
		}

		public String getEntitySatus() {
			return status;
		}

		public long getValue() {
			return value;
		}
		
	}

	public enum SystemSettings {

		REQUEST_RECONCILIATION_JOB_FREQUENCY, REQUEST_RETURNED_TIME_IN_DAYS, PAYMENT_RECONCILIATION_JOB_FREQUENCY,
		DRAFT_REQUEST_VALIDITY_IN_DAYS, SLA_SAP_ADMIN_AUTH_FREQUENCY;
	}

	public enum RequestRejectionReason {

		IDENTITY(1), ELIGIBILITY(2), CONTEXTUAL_VIOLATION_OF_LAWS_AND_REGULATIONS(3), OTHER(4);

		private int symbolicValue;

		RequestRejectionReason(int symbolicName) {
			this.symbolicValue = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicValue;
		}
	}

	public enum SystemNotificationTemplate {

		SUBMISSION_FEES_PAYMENT_PENDING(1), SUBMISSION_FEES_PAYMENT_SUCCESS(2), PRINTING_OFFICE_REQUEST_SUBMISSION(3),
		GOVERNMENT_ENTITY_REQUEST_SUBMISSION_SUCCESS(4), AUDIT_REQUEST_APPROVED_PENDING_PAYMENT(5),
		AUDIT_GOVERNMENT_ENTITY_REQUEST_APPROVED(6), AUDIT_REQUEST_RETURNED(7), AUDIT_REQUEST_CANCELLED_REJECTED(8),
		NOTARY_USER_TRANSFER_REQUEST_ASSIGNED(9), NOTARY_CONFIRM_TRANSFER_VISIT(10),
		APPLICANT_CONFIRM_RESCHEDULE_VISIT(11), TRANSFER_VISIT_CONFIRMATION(12),
		SERVICE_SUBMISSION_PAYMENT_SUCCESS_TRANSLATION(13), SERVICE_SUBMISSION_PAYMENT_SUCCESS_DIGITAL_SIGNATURE(14),
		SERVICE_SUBMISSION_PAYMENT_SUCCESS_MANUAL_SIGNATURE(15), REQUEST_SEND_TRANSLATION_DEPT(16),
		NOTIFICATION_SEND_TO_ARAMEX_DEPARTMENT(17), NOTARY_SUCCESSFULL_NOTIFICATION_TO_PARTY(18),
		ARAMEX_EMPLOYEE_NOTIFICATION_SUCCESS(19), ARAMEX_EMPLOYEE_NOTIFICATION_FAILURE(20),
		ARAMEX_EMPLOYEE_NOTIFICATION_REPORT_TO_NOTARY_EMPLOYEE(21),
		REQUEST_CANCEL_NOTIFICATION_FOR_ARAMEX_FEES_NON_PAYMENT(22), ARAMEX_EMPLOYEE_FINAL_REPORT_NOTIFICATION(23),
		RETURNING_NOTIFICATION_REQUEST_TO_NOTARY_EMPLOYEE(24), ARAMEX_WEEKLY_REPORT_NOTIFICATION_TO_ADMIN(25),
		OFFICE_DEACTIVATION_NOTIFICATION(26), CORPORATE_DEACTIVATION_NOTIFICATION(27),
		NOTARY_CONFIRM_CONFERENCE_SCHEDULE_NOTIFICATION(28), APPLICANT_CONFIRM_CONFERENCE_SCHEDULE_NOTIFICATION(29),
		APPLICANT_NOTARY_CONFIRM_CONFERENCE_SCHEDULE_NOTIFICATION(30), REQUEST_ATTESTATION_COMPLETE_NOTIFICATION(31),
		PUBLICATION_ANNOUNCEMENT_DOCUMENT_ATTESTED_NOTIFICATION(32), PUBLICATION_ANNOUNCEMENT_REJECT_NOTIFICATION(33),
		CREATE_ADDITIONAL_BILLING_DOCUMENT(34), SUCCESSFULL_PAYMENT_ADDITIONAL_BILLING_DOCUMENT(35);

		private int symbolicValue;

		SystemNotificationTemplate(int symbolicName) {
			this.symbolicValue = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicValue;
		}
	}

	public enum DaysOfWeek {
		SUNDAY("Sunday"), MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"),
		FRIDAY("Friday"), SATURDAY("Saturday");

		private String symbolicName;

		DaysOfWeek(String symbolicName) {
			this.symbolicName = symbolicName;
		}

		public String getSymblicName() {
			return symbolicName;
		}
	}

	public enum RequestPreDefinedPayments {

		// more fields ll be added later.
		EXEMPTED_ENTITY_FEE(0), TRANSLATION_REQUIRED(100), SERVICE_FEE_GREATER_THAN_FIFTY_FEE(50),
		CHARGES_ON_HOLIDAY_OR_WEEKEND(200), PAYMENT_VALIDITY_TIME(5), DRAFT_REQUEST_VALIDITY_IN_DAYS(20);

		private float paymentValue;

		RequestPreDefinedPayments(float paymentValue) {
			this.paymentValue = paymentValue;
		}

		public float getPaymentValue() {
			return paymentValue;
		}
	}

	public enum PaymentExemption {
		GOVT_ENTITY(1), MARTYR_FAMILY(2);

		private long exemptionReasonId;

		PaymentExemption(long exemptionReasonId) {
			this.exemptionReasonId = exemptionReasonId;
		}

		public long getPaymentExemption() {
			return exemptionReasonId;
		}
	}

	public enum PaymentExemptionReason {
		GOVT_ENTITY("Government Entities"), MARTYR_FAMILY("Martyr's Family");

		private String exemptionReason;

		PaymentExemptionReason(String exemptionReason) {
			this.exemptionReason = exemptionReason;
		}

		public String getExemptionReason() {
			return exemptionReason;
		}
	}

	public enum PartyType {

		// more fields ll be added later.
		INDIVIDUAL(1), CORPORATE(2),ORGANIZATION(3);

		private int type;

		PartyType(int type) {
			this.type = type;
		}

		public int getTypeValue() {
			return type;
		}
	}

	public enum IdentityType {
		PASSPORT("Passport", "جواز السفر"), EMIRATES_ID("Emirates ID", "الهوية الإماراتية"),
		TRADE_LICENSE("Trade License", "الرخصة التجارية"), UNIFIED_NUMBER("Unified Number", "رقم موحد"),ORGANIZATION("Organization","منظمة");

		private final List<String> values;
		private String symbolicEnglishName;
		private String symbolicArabicName;

		IdentityType(String... values) {
			this.values = Arrays.asList(values);
			this.symbolicEnglishName = this.values.get(0);
			this.symbolicArabicName = this.values.get(1);
		}

		public List<String> getValues() {
			return values;
		}

		public String getSymbolicEnglishName() {
			return symbolicEnglishName;
		}

		public void setSymbolicEnglishName(String symbolicEnglishName) {
			this.symbolicEnglishName = symbolicEnglishName;
		}

		public String getSymbolicArabicName() {
			return symbolicArabicName;
		}

		public void setSymbolicArabicName(String symbolicArabicName) {
			this.symbolicArabicName = symbolicArabicName;
		}

	}

	public enum AppointmentTypeEnum {

		CONFERENCE_CALL("1", "Conference Call"), TRANSFER_MISSION("2", "Transfer Mission"), ON_LEAVE("3", "On Leave"),
		PUBLIC_HOLIDAY("4", "Public Holiday"), OTHERS("5", "Others");

		private String symbolicName;
		private int symbolicValue;

		private final List<String> values;

		public String getSymbolicName() {
			return symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicValue;
		}

		AppointmentTypeEnum(String... values) {
			this.values = Arrays.asList(values);
			this.symbolicValue = Integer.parseInt(this.values.get(0));
			this.symbolicName = this.values.get(1);
		}

		public List<String> getValues() {
			return values;
		}

		public static AppointmentTypeEnum getName(String name) {
			for (AppointmentTypeEnum lang : AppointmentTypeEnum.values()) {
				if (lang.getValues().contains(name)) {
					return lang;
				}
			}
			return null;
		}
	}

	public enum SapPaymentStatus {
		CREATED("CD"), PAID("PD"), NOTPAID("NP"), CANCELLED("CN"), CLEARED("CL");

		private String sapBillingStatus;

		SapPaymentStatus(String sapBillingStatus) {
			this.sapBillingStatus = sapBillingStatus;
		}

		public String getSapBillingStatus() {
			return sapBillingStatus;
		}
	}

	public enum PaymentStatus {
		CREATED(1), PAID(2), NOTPAID(3), CANCELLED(4), CLEARED(5), DEFUALT_QUANTITY(1), DEFAULT_FEE(50);

		private long statusValue;

		PaymentStatus(long statusValue) {
			this.statusValue = statusValue;
		}

		public long getStatusValue() {
			return statusValue;
		}
	}

	public enum PaymentGatewayStatus {
		SUCCESS("Paid"), NOTPAID("UnPaid"), FAILED("Failed"), CANCELLED("Cancelled"), LOCKEDBY("SYSTEM");

		private String gatewayStatus;

		PaymentGatewayStatus(String gatewayStatus) {
			this.gatewayStatus = gatewayStatus;
		}

		public String getGatewayStatus() {
			return gatewayStatus;
		}
	}

	public enum PaymentChannelStatus {
		PAYMENT_SUCCESS(true), PAYMENT_FAILIER(false);

		private boolean channelStatus;

		PaymentChannelStatus(boolean channelStatus) {
			this.channelStatus = channelStatus;
		}

		public boolean getChannelStatus() {
			return channelStatus;
		}
	}

	public enum SapBillingDocumentStatus {
		CREATED("Created"), CLEARED("Cleared"), DELETED("Deleted"), CANCELLED("Cancelled");

		private String sapBillingDocumentStatus;

		SapBillingDocumentStatus(String sapBillingDocumentStatus) {
			this.sapBillingDocumentStatus = sapBillingDocumentStatus;
		}

		public String getSapBillingDocumentStatus() {
			return sapBillingDocumentStatus;
		}
	}

	/*public enum LockTimeOfTransaction {

		TransactionLockTimeInMS(3600000);

		private long lockTime;

		LockTimeOfTransaction(long lockTime) {
			this.lockTime = lockTime;
		}

		public long getLockTime() {
			return lockTime;
		}
	}*/

	public enum RequestPaymentTypes {
		TRANSLATION(1), TRANSFER(2), SUBMISSION(3), HOLIDAY(4), SERVICE(5), ADDITIONAL_FEE(6);

		private long paymentValue;

		RequestPaymentTypes(long paymentValue) {
			this.paymentValue = paymentValue;
		}

		public long getPaymentValue() {
			return paymentValue;
		}
	}

	public enum ReconciliationJobsSettings {
		SLA_REQUEST_RECONCILIATION_JOB_FREQUENCY(1), SLA_MAX_RETURNED_REQUEST_TIME_IN_DAYS(2),
		SLA_PAYMENT_RECONCILIATION_JOB_FREQUENCY(3), DRAFT_REQUEST_VALIDITY_IN_DAYS(4), SLA_MAX_LOCK_MS(5),
		SLA_MAX_PAYMENT_DURATION_IN_DAYS(6), SLA_APPLICATION_LIVE_YEAR(8);

		private long settingValue;

		ReconciliationJobsSettings(long settingValue) {
			this.settingValue = settingValue;
		}

		public long getSettingValue() {
			return settingValue;
		}
	}

	public enum PaymentChannels {
		KIOSK_MACHINE("KIOSK"), CPG("CPG"), SAP("SAP");

		private String channel;

		PaymentChannels(String channel) {
			this.channel = channel;
		}

		public String getChannel() {
			return channel;
		}
	}

	public enum CalculationStage {
		INITIAL_STAGE(1), SECOND_STAGE(2), ADDITIONAL(3);

		private long stage;

		CalculationStage(long stage) {
			this.stage = stage;
		}

		public long getStage() {
			return stage;
		}
	}

	public enum ReconcileEnum {
		RETRY_COUNT(0);

		private long symbolicValue;

		ReconcileEnum(long symbolicValue) {
			this.symbolicValue = symbolicValue;
		}

		public long getSymbolicValue() {
			return symbolicValue;
		}
	}

	public enum RequestFeeAmounts {

		TRANSLATION_FEE(60), TRANSFER_NORMAL_FEE(100), SUBMISSION_FEE(50), TRANSFER_ON_HOLIDAY_FEE(200), SERVICE_FEE(0),
		EXEMPTED_SUBMISSION_FEE(0);

		private int feeType;

		RequestFeeAmounts(int feeType) {
			this.feeType = feeType;
		}

		public int getFeeType() {
			return feeType;
		}
	}

	public enum Gender {

		FEMALE("1", "Female"), MALE("2", "Male");

		private String symbolicName;
		private int symbolicValue;
		private final List<String> values;

		public String getSymbolicName() {
			return symbolicName;
		}

		public List<String> getValues() {
			return values;
		}
		public int getSymbolicValue() {
			return symbolicValue;
		}

		Gender(String ...values) {
			this.values = Arrays.asList(values);
			this.symbolicValue = Integer.parseInt(this.values.get(0));
			this.symbolicName = this.values.get(1);
		}

		public static Gender getName(String name)
		{
			for (Gender lang : Gender.values()) {
				if (lang.getValues().contains(name)) {
					return lang;
				}
			}
			return null;
		}
	}

	public enum Subservices {
		THE_EXECUTIVE_FORMULA(194), RESIDENTIAL_LEASE_CONTRACT(181), COMMERCIAL_LEASE_CONTRACT(182), NOTARY_NOTIFICATION_ATTESTATION(5), POWER_OF_ATTORNEY_CANCELATION(94), SELF_CANCELLATION(237), 
		FACILITY_CONTRACT_OF_CORPORATION_LLC(176),FACILITY_CONTRACT_OF_CIVIL_WORKS_CORPORATION(159), REQUEST_FOR_A_TRANSLATION_VALIDITY(104), REQUEST_A_TRUE_COPY_OF_THE_EDITOR(51), PUBLICATION_REQUEST(210),
		MORTGAGE_CONTRACT_COMMERICAL(42),MORTGAGE_CONTRACT_CIVIL(47), MARRIAGE_CONTRACT_ATT_NON_MUSLIM(48), LIMITED_LIABILITY_COMPANY(57), SINGLE_PERSON_COMPANY(58), TERMINATION_CANCELLATION_OF_CONTRACT(37), 
		PROPERTIES_INVESTEMENT_CONTRACT(218), COMMEERICAL_LICENSE_SALE(220), FACUTLY_CONTRACT_ADDENDUM(172), ADDENDUM_OF_CIVIL_COR_FACITLY_CONTRACT(164), SETTLEMENT_AGREEMENT(92), 
		TRANSPORTATION_RENT_CONTRACT(177), SERVICE_AGENT_HIRING_CONTRACT(158), PRIVATE_POA_FOR_OWEND_PROPERTY(138);
	
		private int subserviceType;

		Subservices(int subserviceType) {
			this.subserviceType = subserviceType;
		}

		public int getSubserviceType() {
			return subserviceType;
		}
	}

	public enum CallResult {
		SUCCESSFULLY_ANNOUNCED(1), UNSUCCESSFULLY_ANNOUNCED(2), CANCELLED(3);

		private long callResult;

		CallResult(long callResult) {
			this.callResult = callResult;
		}

		public long getCallResult() {
			return callResult;
		}

	}

	public enum NotificationStatuses {
		WAITING_FOR_CALL(1), IN_PROGRESS(2), RETURNED_BY_ARAMEX(3), WAITING_FOR_PUBLICATION(4), RETURNED_BY_ANNOUNCEMENT(5),SUCCESSFULLY_ANNOUNCED(6),
		CANCELLED_BY_ANNOUNCEMENT(7), CANCELLED_BY_ARAMEX(8), CANCELLED_BY_SAP_ANNOUNCEMENT(9),UNSUCCESSFULLY_ANNOUNCED(10);
		private long notificationStatus;

		NotificationStatuses(long notificationStatus) {
			this.notificationStatus = notificationStatus;
		}

		public long getNotificationStatus() {
			return notificationStatus;
		}

	}
	public enum AramexAnnouncementResults {

		ANNOUNCED_AND_DELIVERED(1),
		ANNOUNCED_AND_REFUSED_TO_RECEIVE(2),
		NOT_ANNOUNCED(3),
		PASTED(4),
		NOT_PASTED(5),
		NOT_FOUND(6),
		SAME_INFORNATION(7),
		NEW_INFORMATION_FOUND(8);

		private long aramexAnnouncementResult;

		AramexAnnouncementResults(long aramexAnnouncementResult) {
			this.aramexAnnouncementResult = aramexAnnouncementResult;
		}

		public long getAramexAnnouncementResult() {
			return aramexAnnouncementResult;
		}

	} 
	public enum AramexAnnouncementMethod {

		TRANSFER(1),
		STICKING_METHOD(2),
		INVISTIGATIOIN_METHOD(3);

		private long aramexAnnouncementMethod;

		AramexAnnouncementMethod(long aramexAnnouncementMethod) {
			this.aramexAnnouncementMethod = aramexAnnouncementMethod;
		}

		public long getAramexAnnouncementMethod() {
			return aramexAnnouncementMethod;
		}

	}


	public enum AnnouncementMethod {
		AS_PER_THE_ANNOUNCEMENT_SECTION_PROCEDURE(1), BY_ARAMEX(2),BY_PHONE_CALL(3),BY_TRANSFER(4),
		BY_STICKING_ON_THE_COURT_ANNOUNCEMENT_BOARD(5),BY_STICKING_ON_PROPERTY(6),BY_EMAIL(7),BY_FAX(9),
		ACCORDING_ANNOUCEMENT_DEPARTMENT(1), BY_CALLING(3), BY_MOVING(4), POSTING_ON_THE_COURT_ANNOUNCEMENT_BOARD(5);

		private long announcementMethod;

		AnnouncementMethod(long callResult) {
			this.announcementMethod = callResult;
		}

		public long getAnnouncementMethod() {
			return announcementMethod;
		}

	}

	public enum BooleanValues {
		TRUE(true), FALSE(false);

		private boolean booleanValues;

		BooleanValues(boolean booleanValues) {
			this.booleanValues = booleanValues;
		}

		public boolean getBooleanValues() {
			return booleanValues;
		}
	}


	public enum NotifierPersonEnum {

		PERSONALLY(1),
		WIFE(2),
		HUSBAND(3),
		SON(4),
		DAUGHTER(5),
		MOTHER(6),
		FATHER(7),
		SISTER(8),
		BROTHER(9),CORPORATION_REPRESENTATIVE(10),OTHERS(11);

		private long notifierPersonResult;

		NotifierPersonEnum(long notifierPersonResult) {
			this.notifierPersonResult = notifierPersonResult;
		}

		public long getNotifierPersonResult() {
			return notifierPersonResult;
		}

	}
	public enum NotificationType{
		ANNOUNCEMENT("Announcement"), ARAMEX("Aramex"), PUBLICATION("Publication");
		private String notificationType;

		NotificationType(String notificationType) {
			this.notificationType = notificationType;
		}

		public String getNotificationType() {
			return notificationType;
		}
	}
	public enum CallStatus{
		DRAFT("Draft"), FINISH("FINISH");
		private String callStatus;

		CallStatus(String callStatus) {
			this.callStatus = callStatus;
		}

		public String getcallStatus() {
			return callStatus;
		}
	}

	public enum EntityTypeId {
		OFFICE_ID("officeId"), CORPORATION_ID("corporationId"), GOVERNMENT_ID("governmentEntityId");


		private String symbolicName;

		EntityTypeId(String type) {
			this.symbolicName = type;
		}

		public String getSymbolicName() {
			return symbolicName;
		}
	}

	public enum ApplicationForm {
		ARABIC("Arabic"), ARABIC_ENG("Arabic/English");

		private String symbolicName;

		ApplicationForm(String type) {
			this.symbolicName = type;
		}

		public String getSymbolicName() {
			return symbolicName;
		}
	}

	public enum ConferenceStatus {
		// Cisco has three stages Pending, Confirm, Completed
		PENDING("Pending"), CONFIRM("Confirm"), BOOKED("Booked"), STARTED("Started"), ENDED("Ended"), COMPLETED("Completed"), CANCELLED("Cancelled"),
		PAUSE("Pause"), REJOIN("Rejoin");

		private String meetingStatus;

		ConferenceStatus(String meetingStatus) {
			this.meetingStatus = meetingStatus;
		}

		public String getMeetingStatusValue() {
			return meetingStatus;
		}
	}

	public enum CiscoErrorCodes {

		LICENCE_ERROR(601), START_CALL_ERROR(611), END_CALL_ERROR(612), BOOKING_SESSION_NOT_ACTIVE(614);
		private int symbolicName;

		CiscoErrorCodes(int symbolicName) {
			this.symbolicName = symbolicName;
		}

		public int getSymbolicValue() {
			return symbolicName;
		}
	}
	//As per discussion with UI team they need full name not the shortName so i commented these lines and written below Enum
	//	public static Map<String, String> getQueueNameAndShortName() {
	//		Map<String, String> queuesName = new HashedMap();
	//		queuesName.put("FN_Notary_ElectronicQueue", "ER");
	//		queuesName.put("FN_Notary_PrintingOfficeQueue", "POR");
	//		queuesName.put("FN_Notary_PaymentQueue", "PR");
	//		queuesName.put("FN_Notary_TransferQueue", "TR");
	//		queuesName.put("FN_Notary_ApplicantReviewQueue", "ARQ");
	//		queuesName.put("FN_Notary_TranslationQueue", "TR");
	//		queuesName.put("FN_Notary_PartySignatureQueue", "PSR");
	//		queuesName.put("FN_Notary_AttestationQueue", "AR");
	//		queuesName.put("FN_Notary_NotificationQueue", "NR");
	//		queuesName.put("FN_Notary_Transfer_ApplicantRescheduleQueue", "TAR");
	//		queuesName.put("FN_Notary_HOA", "HOAR");
	//		queuesName.put("FN_Notary_ArchivingQueue", "ARR");
	//		return queuesName;
	//	}

	public enum DocumentStatus {

		VALID("Valid"), CANCELLED("Cancelled"), EXPIRED("Expired"), PARTIAL("Partial Valid"), REJECTED("Rejected"), UNKNOWN("Unknown"),COMPLETED("Completed");

		DocumentStatus(String symbolicName) {
			this.symbolicName = symbolicName;
		}

		private String symbolicName;

		public String getValue() {
			return symbolicName;
		}
	}


	public enum ExecutiveFormulaEnum {
		PAY_ALL_AT_ONCE("Pay All"), PAYMENT_POSTPONE("Payment Postpone"), PARTIAL_PAYMENT_POSTPONE("Partial Payment Postpone"), REJECT("Rejected");

		private String symbolicName;

		ExecutiveFormulaEnum(String symbolicName) {
			this.symbolicName = symbolicName;
		}
		public String getValue() {
			return symbolicName;
		}
	}

	public enum ExecutiveFormulaIdEnum {
		PAY_ALL_AT_ONCE(1), PAYMENT_POSTPONE(2), PARTIAL_PAYMENT_POSTPONE(3), REJECT(4), REJECT_FEE(500);

		private int paymentScheme;

		ExecutiveFormulaIdEnum(int paymentScheme) {
			this.paymentScheme = paymentScheme;
		}
		public int getPaymentScheme() {
			return paymentScheme;
		}
	}


	public enum AttorneySourceType {
		INSIDE_RAK("1", "Inside RAK", "داخل رأس الخيمة"), OUTSIDE_RAK("2", "Outside RAK", "خارج رأس الخيمة");
		private long attorneySource;
		private String symbolicEnglishName;
		private String symbolicArabicName;
		private final List<String> values;

		AttorneySourceType(String... values) {
			this.values = Arrays.asList(values);
			this.attorneySource = Integer.parseInt(this.values.get(0));
			this.symbolicEnglishName = this.values.get(1);
			this.symbolicArabicName = this.values.get(2);
		}

		public long getAttorneySource() {
			return attorneySource;
		}

		public String getSymbolicEnglishName() {
			return symbolicEnglishName;
		}

		public void setSymbolicEnglishName(String symbolicEnglishName) {
			this.symbolicEnglishName = symbolicEnglishName;
		}

		public String getSymbolicArabicName() {
			return symbolicArabicName;
		}

		public void setSymbolicArabicName(String symbolicArabicName) {
			this.symbolicArabicName = symbolicArabicName;
		}

		public static AttorneySourceType getAttorneySourceType(int k) {
			for (AttorneySourceType lang : AttorneySourceType.values()) {
				if (lang.getAttorneySource()==(k)) {
					return lang;
				}
			}
			return null;
		}
	}

	public enum GlobalSearchType {
		BASIC("Basic"), ADVANCE("Advance");

		private String value;

		GlobalSearchType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
	public enum SchedlueStatus {
		NOTARY_CONFIRMATION("notary confirmation"), APPLICANT_CONFIRMATION("applicant confirmation"), 
		COMPLETED("completed");

		private String name;

		SchedlueStatus(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	public enum MainServices {
		PUBLIC_POWER_OF_ATTORNEY(3), PRIVATE_POWER_OF_ATTORNEY(4),POWER_OF_ATTORNEY_CANCELLATION(5);

		private int value;

		MainServices(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}


	}

	public enum EmiratesCountryCode{
		UAE("AE");

		private String value;

		EmiratesCountryCode(String value) {
			this.value = value;
		}

		public String getCountryCode() {
			return value;
		}

	}

	public enum SubserviceFeeTypes {

		TRANSLATION_FEE(1), TRANSFER_FEE_NORMAL(2), SUBMISSION_FEE(3), TRANSFER_FEE_ON_HOLIDAY(4), SERVICE_FEE(5), ADDITIONAL_FEE(6);

		private long value;

		SubserviceFeeTypes(long value) {
			this.value = value;
		}

		public long getValue() {
			return value;
		}
	}

	public enum BashrService {
		SINGLE_PERSON_COMPANY("Single person company"), LIMITED_LIABILITY_COMPANY("Limited liability company");
		private String serviceName;

		BashrService(String serviceName) {
			this.serviceName = serviceName;
		}

		public String getServiceName() {
			return serviceName;
		}
	}

	public enum ClassificationEnum {

		CONTRACT(40), POWER_OF_ATTHORNEY(31), REQUEST(35);

		private long classificationId;

		ClassificationEnum(long classificationId) {
			this.classificationId = classificationId;
		}

		public long getClassificationId() {
			return classificationId;
		}
	}

	public enum AuthSourceFNChoiceList {
		INTERNAL("Internal"), EXTERNAL("External");
		private String attorneySource;
		AuthSourceFNChoiceList(String attorneySource) {
			this.attorneySource = attorneySource;
		}
		public String getAttorneySource() {
			return attorneySource;
		}
	}

	public enum BPFunctionType {
		ANNOUNCER("Y0000580"), ANNOUNCEFOR("Y0000581");
		private String announcerForTypeCode;

		public String getAnnouncerForTypeCode() {
			return announcerForTypeCode;
		}
		BPFunctionType(String announcerForTypeCode) {
			this.announcerForTypeCode = announcerForTypeCode;
		}
	}


	public enum SapAnnouncementType {

		ATTORNEY_CANCEL("ANN50", "Power of Attorney Cancelation"), SELF_CANCEL("ANN51", "Self-Cancellation"), 
		NOTARY_NOTIFICATION_ATTESTATION("ANN52", "Notary Notification Attestation");

		private String symbolicName;
		private String symbolicCode;

		SapAnnouncementType(String symbolicCode,String symbolicName) {
			this.symbolicName = symbolicName;
			this.symbolicCode = symbolicCode;
		}
		public String getSymbolicCode() {
			return symbolicCode;
		}
		public String getSymbolicName() {
			return symbolicName;
		}


	}

	public enum SapAttachementCode {

		ANNOUNCEMENT_FILE("Announcement File", "1000"), ANNOUNCEMENT_RESULT("Announcement Result", "1001"), 
		SUPPORTED_ATTACHMENTS("Supported Attachments", "1002");

		private String symbolicName;
		private String symbolicCode;

		SapAttachementCode(String symbolicName,String symbolicCode) {
			this.symbolicName = symbolicName;
			this.symbolicCode = symbolicCode;
		}
		public String getSymbolicCode() {
			return symbolicCode;
		}
		public String getSymbolicName() {
			return symbolicName;
		}


	}

	public enum SapAnnouncementResponse {

		SUBMITTED_SUCCESSFULLY("Submitted Successfully", "002"), FAILED_SUBMISSION("Failed Submission", "001");

		private String symbolicName;
		private String symbolicCode;

		SapAnnouncementResponse(String symbolicName,String symbolicCode) {
			this.symbolicName = symbolicName;
			this.symbolicCode = symbolicCode;
		}
		public String getSymbolicCode() {
			return symbolicCode;
		}
		public String getSymbolicName() {
			return symbolicName;
		}


	}

	public enum DocSourceType {

		NORMAL(0), PORTAL(1), MOBILE(2);

		private int docSourceType;

		DocSourceType(int docSourceType) {
			this.docSourceType = docSourceType;
		}

		public int getSourceType() {
			return docSourceType;
		}

		public int getValue() {
			return docSourceType;
		}
	}
	
	public enum SapAnnouncementListType {

		SENT_TO_ANNOUNCEMENT_DEPARTMENT("0001"), RETURN_FROM_ANNOUNCEMENT_DEPARTMENT("0002"), 
		COMPLETE_ANNOUNCEMENT("0003"), CANCELED_ANNOUNCEMENT("0004"), ALL_ANNOUNCEMENT("0005");

		private String symbolicCode;

		SapAnnouncementListType(String symbolicCode) {
			this.symbolicCode = symbolicCode;
		}
		public String getSymbolicCode() {
			return symbolicCode;
		}


	}
	
	public enum RequestAttachment {
		COPY_OF_THE_MORTGAGE_CONTRACT(72),  TRANSLATED_DOCUMENT_SEALED_BY_THE_TRANSLATION_OFFICES_IN_THE_MINISTRY(70),
		COPY_OF_THE_MARRIAGE_CONTRACT(37), RENTAL_CONTRACT_CERTIFIED_BY_THE_MUNICIPALITY(69), COPY_OF_THE_ATTESTED_REQUEST(32);
	
		private long requestAttachment;

		RequestAttachment(long requestAttachment) {
			this.requestAttachment = requestAttachment;
		}

		public long getRequestAttachment() {
			return requestAttachment;
		}
	}
	
	public enum SystemNotifications {
		SUBMISSION_FEES_PAYMENT_PENDING(1),  SUBMISSION_FEES_PAYMENT_SUCCESS(2), REQUESET_CANCEL_NOTIFICATION_FOR_ARAMEX_FEES_NON_PAYMENT(22),
		GOVERNMENT_ENTITY_REQUEST_SUBMISSION_SUCCESS(4), OFFICE_DEACTIVATION_NOTIFICATION(26), CORPORATE_DEACTIVATION_NOTIFICATION(27),
		APPLICANT_NOTARY_CONFIRM_CONFERENCE_SCHEDULE_NOTIFICATION(30), NOTARY_CONFIRM_CONFERENCE_SCHEDULE_NOTIFICATION(28),
		APPLICANT_CONFIRM_CONFERENCE_SCHEDULE_NOTIFICATION(29), PRINTING_OFFICE_REQUEST_SUBMISSION(3);
		private long systemNotificationId;

		SystemNotifications(long systemNotificationId) {
			this.systemNotificationId = systemNotificationId;
		}

		public long getSystemNotificationId() {
			return systemNotificationId;
		}

	}
	public enum AccessType{
		OVERRIDE_DUPLICATED_ATTESTATION_NUMBER("Override_Duplicated_Attestaiton_Number"),OVERRIDE_DUPLICATED_ATTESTAITON_NUMBER("Override_Duplicated_Attestation_Number");
		private String accessType;
		AccessType(String accessType) {
			this.accessType = accessType;
		}
		public String getAccessType() {
			return accessType;
		}

	}
	
	public enum EnvironmentType{
		TEST("test"),DEV("dev"),SIT("sit"), PROD("prod");
		private String envType;
		EnvironmentType(String envType) {
			this.envType = envType;
		}
		public String getEnvType() {
			return envType;
		}
	}
	
	public enum SapAnnouncementStatus {

		NEW("E0001"), CANCEL("E0002"), 
		IN_PROCESS("E0007"), ANNOUNCEMENT_COMPLETE("E0010"), ANNOUNCEMENT_INCOMPLETE("E0011"), ANNOUNCEMENT_RETURN("E0012");

		private String symbolicCode;

		SapAnnouncementStatus(String symbolicCode) {
			this.symbolicCode = symbolicCode;
		}
		public String getSymbolicCode() {
			return symbolicCode;
		}


	}
	
	public enum PartyTypes {

		INDIVIDUAL("Individual", " فرد"), CORPORATION("Corporation", " شركة"), 
		ORGANIZATION("Organization", "منظمة");

		private String partyName;
		private String partyNameAr;

		PartyTypes(String partyName,String partyNameAr) {
			this.partyName = partyName;
			this.partyNameAr = partyNameAr;
		}
		public String getPartyNameAr() {
			return partyNameAr;
		}
		public String getPartyName() {
			return partyName;
		}
	}
	
	public enum EODB {
		GENDER_TYPE_ID(7), LEGAL_TYPE(17), LICENSE_TYPE(18), OWNER_TYPE(19), COUNTRIES_INVESTOR(20), EMIRATES_INVESTOR(21), CITIES_INVESTOR(22),
		COUNTRIES_ESTABLISHMENT(23), EMIRATES_ESTABLISHMENT(24), CITIES_ESTABLISHMENT(25);
		private long typeId;

		EODB(long typeId) {
			this.typeId = typeId;
		}

		public long getTypeId() {
			return typeId;
		}
	}
}
