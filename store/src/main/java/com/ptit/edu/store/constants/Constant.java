package com.ptit.edu.store.constants;

public class Constant {
    //CODE của các thứ trong tuần
    public static final int MONDAY = 2;
    public static final int TUESDAY = 3;
    public static final int WEDNESDAY = 4;
    public static final int THURSDAY = 5;
    public static final int FRIDAY = 6;
    public static final int SATURDAY = 7;
    public static final int SUNDAY = 8;

    //CODE của các buổi trong ngày
    public static final int MORNING = 0;
    public static final int AFTERNOON = 1;
    public static final int EVENING = 2;

    //CODE của giới tính
    public static final int FEMALE = 0;
    public static final int MALE = 1;
    public static final int BOTH = 2;

    public static final String PENDING = "pending";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    public static final String NONE = "none";

    //CODE của đơn vị lương
    public static final String SALARY_PRODUCT = "sản phẩm";
    public static final String SALARY_SHIFT = "ca";
    public static final String SALARY_HOUR = "giờ";
    public static final String SALARY_MONTH = "tháng";

    public static final String HIBERNATE_LAZY_INITIALIZER = "hibernateLazyInitializer";
    public static final String HANDLER = "handler";

    public static final int REMIND_BY_REGION_MODE = 1;
    public static final int REMIND_BY_LOCATION_MODE = 0;

    //Kích thước số phần tử trả về tối đa trên 1 trang
    public static final int MAX_PAGE_SIZE = 10;
    public static final long MAX_CANDIDATE_RECOMENDED = 5;

    //Số ngày hết hạn của 1 job
    public static final int REMAINING_DAY_OF_JOB = 7;

    //Khoảng điểm số của chứng chỉ ngoại ngữ
    public static final double MIN_LANGUAGE_SKILL_SCORE = 0.0;
    public static final double MAX_LANGUAGE_SKILL_SCORE = 100.0;

    public static final String COORDINATES = "coordinates";
    public static final String FIELDS = "fields";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String MALE_STRING = "male";
    public static final String FEMALE_STRING = "female";

    public static final String EMAIL = "email";
    public static final String FACEBOOK = "facebook";
    public static final String GOOGLE_PLUS = "google_plus";
    public static final String HEADQUARTERS = "Cơ sở chính";

    public static final int PREVIEW_DESCRIPTION_TEXT_COUNT = 256;

    public static final String GOOGLE_API_KEY = "AIzaSyAXRkarAEcuEMm-scFg0VGVCuQgyJJkXPM";
    public static final String KEY = "key";
    public static final String LATLNG = "latlng";
    public static final String SENSOR = "sensor";
    public static final String MAP_UNNAMED_ROAD = "Unnamed Road";

    public static final int MORNING_END_TIME_IN_MINUTE = 12 * 60 + 59;
    public static final int AFTERNOON_END_TIME_IN_MINUTE = 17 * 60 + 59;
    public static final int EVENING_START_TIME_IN_MINUTE = 18 * 60;

    public static final long A_MONTH_IN_MILLIS = 2592000000L;
    public static final int A_DAY_IN_MILLIS = 86400000;
    public static final int AN_HOUR_IN_MILLIS = 3600000;

    public static final String MONTH = "tháng";
    public static final String DAY = "ngày";
    public static final String HOUR = "giờ";
    public static final String LEFT = "Còn";
    public static final String AGO = "trước";
}
