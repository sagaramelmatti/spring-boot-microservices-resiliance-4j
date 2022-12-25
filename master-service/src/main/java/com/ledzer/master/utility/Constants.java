/**
 * 
 */
package com.ledzer.master.utility;

import org.springframework.data.domain.Sort.Direction;

/**
 * @author Chinna
 *
 */
public class Constants {

	public static final String SUCCESS = "success";
	
	public static final String FAILURE = "failure";
	
	public static final String ROLE_STUDENT = "ROLE_STUDENT";
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ROLE_JIEM_ADMIN = "ROLE_JIEM_ADMIN";
	
	public static final String ROLE_ACADEMIC_USER = "ROLE_ACADEMIC_USER";
	
	public static final String ROLE_SCHOOL_ADMIN = "ROLE_SCHOOL_ADMIN";
	
	public static final String ROLE_COORDINATOR = "ROLE_COORDINATOR";
	
	public static final String ROLE_TEACHER = "ROLE_TEACHER";
	
	public static final String STUDENT_ROLE = "STUDENT";
	
	public static final Boolean CONSTANT_TRUE = true;
	
	public static final Boolean CONSTANT_FALSE = false;
	
	public static final String COLON = ":";
	
	/** Application properties file. */
	public static final String APPLICATION_PROPERTIES_FILENAME = "commonApp.properties";

	/** Amazon properties file name. */
	public static final String AMAZON_PROERTIES_FILENAME = "amazon.properties";

	/**
	 * Default limit on page number.
	 */
	public static final int PAGE_LIMIT_DEFAULT = 10;

	/** Maximum page limit. */
	public static final int PAGE_LIMIT_MAX = 500;

	/** Default page offset. */
	public static final int PAGE_OFFSET_DEFAULT = 0;

	/** Maximum page offset. */
	public static final int PAGE_OFFSET_MAX_LIMIT = 500;

	/** Version prefix for API. */
	public static final String VERSION_PEFIX = "v";

	/** User API version 1. */
	public static final int USER_VERSION_1 = 1;

	/** Date format. */
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/** Default sort order for sorting. */
	public static final Direction DEFAULT_SORT_ORDER = Direction.DESC;

	/** Default sort column for sorting. */
	public static final String DEFAULT_SORT_COLUMN = "lastModifiedTime";

	/** Dynamic filter operator allowed regular expression. */
	public static final String FILTER_OPERATOR_SUPPORTED = "(<=)|(>=)|(=)|(!=)|(<)|(>)";

	/** Query AND constant. */
	public static final String QUERY_AND = " AND ";

	/** Query OR constant. */
	public static final String QUERY_OR = " OR ";

	/** Query parameter prefix. */
	public static final String QUERY_PARAM_PREFIX = " $";

	/** Query IN constant. */
	public static final String QUERY_IN = " IN ";

	/** Query ANY constant. */
	public static final String QUERY_ANY = " ANY ";

	/** Query Parameters equals constant. */
	public static final String QUERY_PARAM_EQUALS_PREFIX = " =" + QUERY_PARAM_PREFIX;

	/** Query Greater then equals constant. */
	public static final String QUERY_PARAM_GREATER_EQUALS_PREFIX = " >=" + QUERY_PARAM_PREFIX;

	/** Query less then equals constant. */
	public static final String QUERY_PARAM_LESSTHEN_EQUALS_PREFIX = " <=" + QUERY_PARAM_PREFIX;

	/** Query Parameter In constant. */
	public static final String QUERY_PARAM_IN_PREFIX = QUERY_IN + QUERY_PARAM_PREFIX;

	/** Query order Direction constant. */
	public static final String QUERY_ORDER_DIRECTION = "direction";

	/** Query order column constant. */
	public static final String QUERY_ORDER_COLUMN_PARAM = "columnName";

	/** Query limit constant. */
	public static final String QUERY_ORDER_LIMIT_PARAM = "limit";

	/** Query offset constant.. */
	public static final String QUERY_ORDER_OFFSET_PARAM = "offset";

	/** Query count constant.. */
	public static final String QUERY_ORDER_COUNT_PARAM = "count";
	
	/** Query size constant.. */
	public static final String QUERY_ORDER_SIZE_PARAM = "size";
	
	/** The Constant Space String. */
	public static final String SPACE_STRING = " ";

	/** Query limit offset order by constant. */
	public static final String QUERY_LIMIT_OFFSET_OREDERBY = " ORDER BY " + "#{#" + QUERY_ORDER_COLUMN_PARAM + "}"
			+ SPACE_STRING + "#{#" + QUERY_ORDER_DIRECTION + "}" + " LIMIT" + QUERY_PARAM_PREFIX
			+ QUERY_ORDER_LIMIT_PARAM + " OFFSET" + QUERY_PARAM_PREFIX + QUERY_ORDER_OFFSET_PARAM;

	/** The Constant QUERY_CHILD. */
	public static final String QUERY_CHILD = "child";

	/** The Constant QUERY_ANY_IN_CHILD. */
	public static final String QUERY_ANY_IN_CHILD = " ANY " + QUERY_CHILD + " IN ";

	/** The Constant QUERY_SATIESFIES. */
	public static final String QUERY_SATIESFIES = " SATISFIES ";

	/** The Constant QUERY_END. */
	public static final String QUERY_END = " END ";

	/** The constant QUERY ROUND BRACKET START. */
	public static final String QUERY_START_ROUND_BRACKET = "(";

	/** The constant QUERY ROUND BRACKET END. */
	public static final String QUERY_END_ROUND_BRACKET = ")";

	/** The Constant PLACE_HOLDER. */
	public static final String PLACE_HOLDER = "{0}";

	/** The Constant QUERY_RADIANS. */
	public static final String QUERY_RADIANS = "RADIANS(" + PLACE_HOLDER + ")";

	/** The Constant QUERY_ACOS. */
	public static final String QUERY_ACOS = "acos";

	/** The Constant QUERY_SIN. */
	public static final String QUERY_SIN = "sin";

	/** The Constant QUERY_COS. */
	public static final String QUERY_COS = "cos";

	/** The Constant INSTANCE_RADIUS_IN_METERS. */
	public static final String INSTANCE_RADIUS_IN_METERS = "radiusInMeters";

	/**
	 * The Constant for pType variable used in query for personTypes in person
	 * entity.
	 */
	public static final String QUERY_ARRAY_PTYPE_VARIABLE = "pType";

	// Cache constants used in application

	/**
	 * The Constant CACHE_NAME_PROPERTY_KEY is property file key for cache name.
	 */
	public static final String CACHE_NAME_PROPERTY_KEY = "cache.cacheName";

	/** The Constant CACHE_NAME_COUCHBASE is cache of CouchBase type. */
	public static final String CACHE_NAME_COUCHBASE = "Couchbase";

	/** The Constant CACHE_NAME_HAZELCAST is cache of HazelCast type. */
	public static final String CACHE_NAME_HAZELCAST = "Hazelcast";

	/**
	 * The Constant CACHE_NAME_CONCURRENT_MAP is cache of ConcurrentMap type.
	 */
	public static final String CACHE_NAME_CONCURRENT_MAP = "ConcurrentMap";


	/* Amazon Properties */
	/**
	 * The Constant S3_BUCKET_NAME_VARIABLE used as variable name for Amazon S3
	 * bucket.
	 */
	public static final String S3_BUCKET_NAME_VARIABLE = "s3BucketName";

	/**
	 * The Constant S3_FOLDER_NAME_VARIABLE used as variable name for Amazon S3
	 * Folder.
	 */
	public static final String S3_FOLDER_NAME_VARIABLE = "s3FolderName";

	/**
	 * The Constant AMAZON_ACCESS_KEY is used as property name for access key
	 */
	public static final String AMAZON_ACCESS_KEY = "amazon.accessKey";

	/**
	 * The Constant AMAZON_SECRET_KEY is used as property name for secret key.
	 */
	public static final String AMAZON_SECRET_KEY = "amazon.secretKey";

	/**
	 * The Constant AMAZON_S3_REGION_NAME is used as property name for region.
	 */
	public static final String AMAZON_S3_REGION_NAME = "amazon.s3.regionName";

	/**
	 * The Constant AMAZON_EMAIL_REGION_NAME is used as property name for email
	 * service region.
	 */
	public static final String AMAZON_EMAIL_REGION_NAME = "amazon.email.regionName";

	// User authentication related constants

	/** The Constant encrypted SALT for password. */
	public static final String SALT = "ca3IJEkYqnTew8YCokVW";

	/** The Constant USERNAME_DELIMITER. */
	public static final String USERNAME_DELIMITER = "||";

	/** The Constant USERNAME_DELIMITER_SPLITER. */
	public static final String USERNAME_DELIMITER_SPLITER = "\\|\\|";

	/**
	 * The Constant TOKEN_32_BIT is used to generate 32 bit code random string.
	 */
	public static final char[] TOKEN_32_BIT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	/** The Constant PASSWORD_GRANT_TYPE. */
	public static final String PASSWORD_GRANT_TYPE = "password";

	/** The Constant AUTHORIZATION_CODE_GRANT_TYPE. */
	public static final String AUTHORIZATION_CODE_GRANT_TYPE = "authorization_code";

	/** The Constant REFRESH_TOKEN_GRANT_TYPE. */
	public static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

	/**
	 * Application property file key for forgot password token expiration in
	 * seconds
	 */
	public static final String FORGOTPASSORD_TOKEN_EXPIRY_SECONDS = "forgotPassword.token.expiryInSeconds";

	/** The Constant REPLACE_PREFIX used for Template replace operation */
	public static final String REPLACE_PREFIX = "$";

	/** The Constant EVENT_TYPE_ATTRIBUTES_KEY_ACTION. */
	public static final String EVENT_TYPE_ATTRIBUTES_KEY_ACTION = "action";

	/** The Constant EVENT_ATTRIBUTES_KEY_NAME. */
	public static final String EVENT_ATTRIBUTES_KEY_NAME = "name";

	/** The Constant EVENT_ATTRIBUTES_KEY_LOCATION. */
	public static final String EVENT_ATTRIBUTES_KEY_LOCATION = "location";
	
	public static final String PERSON_SOURCE_LOCAL = "Local";

	public static final String DEMO_EXAM = "demo";

	public static final Short INTEGER_ZERO = 0;
	
	public static final Short INTEGER_ONE = 1;

	public static final Short INTEGER_MINUTE_IN_SECONDS = 60;
	
	public static final String SPLIT_FOR_INSTRUCTION = "<br>";
	
	/*
	 * User constants
	 */
	public static final String users = "users";
	
	/*
	 * School constants
	 */
	public static final String schools = "schools";
	
	public static final String exams = "exams";

	public static final String EASY = "Easy";
	
	public static final String MEDIUM = "Medium";
	
	public static final String DIFFICULT = "Difficult";
	
	public static final String HYPHEN = "-";

	public static final CharSequence oneUpDefaultUserPassword = "oneup@123";
	
	public static final String schoolEntitySearchFields = "schoolName,schoolCode,address,faxNumber,phoneNumber,pinCode,website,board,contactEmailId,contactPerson";
	public static final String userEntitySearchFields = "alternateMobile,email,firstName,lastName,mobile,oneUpId,username";
	public static final String schoolProductSearchFields = "schoolProductKey,description";
	public static final String examEntitySearchFields = "examName";
	public static final String classTeacherAssociationEntitySearchFields = "grade,section";
	public static final String examTemplateEntitySearchFields = "templateName";

	public static final String regexForName="[a-zA-Z]+";

	public static final String MATHS_SUBJECT_DEMO="Maths Demo";

	public static final String MATHS_SUBJECT="Maths";

	public static final String PHYSICS_SUBJECT_DEMO="Physics Demo";

	public static final String PHYSICS_SUBJECT="Physics";

	public static final String CHEMISTRY_SUBJECT="Chemistry";

	public static final String SCIENCE_SUBJECT="Science";

	public static final String CHEMISTRY_SUBJECT_DEMO="Chemistry_Demo";

	public static final String SUBJECT_CODE_FOR_MATHS="01";

	public static final String SUBJECT_CODE_FOR_SCIENCE="02";

	public static final String SUBJECT_CODE_FOR_PHYSICS="03";

	public static final String SUBJECT_CODE_FOR_CHEMISTRY="04";

	public static final String SUBJECT_CODE_FOR_NULL="00";

	public static final String SUBJECT_CODE_FOR_SEQUENCE="0001";

	public static String locked = "LOCKED";

	public static String unLocked = "UNLOCKED";

	public static String demoExamType = "Demo";

	public static final String mathematics = "Mathematics";

	public static final String science = "Science";

	public static final String physics = "Physics";

	public static final String chemistry = "Chemistry";

	public static final String biology = "Biology";													
	public static final String NUMBERS_FROM_ONE = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50";
	
	public static final String underscore = "_";

	public static final String questionBankCode = "QB";

	public static final String delimiterDot = ".";

	/*
	 * Constants for questions skill details
	 */
	public static final String conceptualProficiency = "Conceptual Proficiency";

	public static final String computation = "Computation";

	public static final String memorization = "Memorization";

	public static final String problemSolving = "Problem Solving";

	public static final String algorithmicThinking = "Algorithmic Thinking";

	public static final String comprehension = "Comprehension";

	public static final String analyticalThinking = "Analytical Thinking";

	/*
	 * Constants for questions rbt details
	 */
	public static final String rembering = "Remembering";

	public static final String understanding = "Understanding";

	public static final String applying = "Applying";

	public static final String analysing = "Analysing";

	public static final String evaluating = "Evaulating";

	public static final String creating = "Creating";

	public static final String YOURSCORE = "Your Score";

	public static final String AVERAGESCORE = "National Average Score";

	public static final String overall = "Overall";

	public static final String delimiterHiphen = "-";

	public static final String dlForK1 = "K1";
	public static final String dlForK2 = "K2";
	public static final String dlForK3 = "K3";
	public static final String dlForK4 = "K4";
	public static final String dlForK5 = "K5";
	public static final String dlForA1 = "A1";
	public static final String dlForA2 = "A2";
	public static final String dlForA3 = "A3";
	public static final String dlForA4 = "A4";
	public static final String dlForA5 = "A5";
	
	/* Constant for Report */
	
	public static final String uploadreportpath = "src/main/resources/static/upload/";
	
	public static final String legend = "src/main/resources/static/images/overallscore_new.jpg";
	
	public static final String rd_logo = "src/main/resources/static/images/rd_logo.jpg";
	
	public static final String oneup_logo = "src/main/resources/static/images/logo.jpg";
	
	public static final String SchoolNotSelected = "SchoolNotSelected";
	
}

