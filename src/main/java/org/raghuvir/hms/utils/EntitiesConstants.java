package org.raghuvir.hms.utils;

import java.io.File;

public final class EntitiesConstants {

	public static final int REGULAR_PRICEPERBED = 1500, GENERAL_PRICEPERBED = 500, VIP_PRICEPERBED = 3000,
			VIP_TOTALBEDS = 1, GENERAL_TOTALBEDS = 6, REGULAR_TOTALBEDS = 3;

	public static final String VIP = "VIP", REGULAR = "REGULAR", GENERAL = "GENERAL", DOCTOR = "DOCTOR",
			STAFF = "STAFF", RECEPTIONIST = "RECEPTIONIST", ADMIN = "ADMIN", PATIENT = "PATIENT", ROOM = "ROOM",
			HMSUSER = "HMSUSER";


	public static final String PATIENT_PREFIX = "P", DOCTOR_PREFIX = "D", STAFF_PREFIX = "S", ADMIN_PREFIX = "ADMIN";
	public static final String PENDING = "PENDING", CHECKED = "CHECKED", CONFIRMED = "CONFIRMED",
			CANCELLED = "CANCELLED";

	public static int getTotalBEDS(String type) {
		if (REGULAR.equalsIgnoreCase(type) || type.startsWith("RR")) {
			return REGULAR_TOTALBEDS;
		}
		if (VIP.equalsIgnoreCase(type) || type.startsWith("RV")) {
			return VIP_TOTALBEDS;
		} else {
			return GENERAL_TOTALBEDS;
		}
	}

	public static int getPricePerBed(String type) {
		if (REGULAR.equalsIgnoreCase(type) || type.startsWith("RR")) {
			return REGULAR_PRICEPERBED;
		}
		if (VIP.equalsIgnoreCase(type) || type.startsWith("RV")) {
			return VIP_PRICEPERBED;
		} else {
			return GENERAL_PRICEPERBED;
		}
	}

	public static String getValidRoomType(String type) {
		if (REGULAR.equalsIgnoreCase(type) || type.startsWith("RR")) {
			return REGULAR;
		}
		if (VIP.equalsIgnoreCase(type) || type.startsWith("RV")) {
			return VIP;
		} else {
			return GENERAL;
		}
	}

	public String getDOCTOR() {
		return "DOCTOR";
	}

	public String getSTAFF() {
		return "STAFF";
	}

	public String getPATIENT() {
		return "PATIENT";
	}

	public String getROOM() {
		return "ROOM";
	}

}
