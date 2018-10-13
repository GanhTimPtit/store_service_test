package com.ptit.edu.store.constants;


import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DataValidator {
    private static final String PHONE_REGEX = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})";

    public static final String SALARY_PRODUCT_FORMAT = "sản phẩm";
    public static final String SALARY_SHIFT_FORMAT = "ca";
    public static final String SALARY_HOUR_FORMAT = "giờ";
    public static final String SALARY_MONTH_FORMAT = "tháng";

    public static final String DATE_FORMAT = "dd/MM/YYYY";

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber == null || phoneNumber.matches(PHONE_REGEX);
    }

    public static boolean isScoreValid(double score) {
        return Constant.MIN_LANGUAGE_SKILL_SCORE <= score && score <= Constant.MAX_LANGUAGE_SKILL_SCORE;
    }

    public static boolean isDayOfWeekIDsValid(Set<Integer> dayOfWeekIDs) {
        if (dayOfWeekIDs == null || dayOfWeekIDs.contains(null)) {
            return false;
        }
        for (Integer dayOfWeekID : dayOfWeekIDs) {
            if (Constant.MONDAY > dayOfWeekID || dayOfWeekID > Constant.SUNDAY) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGenderValid(Integer gender) {
        if (gender == null) {
            return false;
        }
        switch (gender) {
            case Constant.FEMALE:
            case Constant.MALE:
            case Constant.BOTH: {
                return true;
            }

            default: {
                return false;
            }
        }
    }

    public static int getGender(String gender) {
        if(gender == null) {
            return Constant.BOTH;
        }
        switch (gender.toLowerCase()) {
            case Constant.MALE_STRING: {
                return Constant.MALE;
            }

            case Constant.FEMALE_STRING: {
                return Constant.FEMALE;
            }

            default: {
                return Constant.BOTH;
            }
        }
    }



    public static boolean isSalaryUnitValid(String unit) {
        if (unit == null) {
            return true;
        }
        switch (unit) {
            case SALARY_HOUR_FORMAT:
            case SALARY_PRODUCT_FORMAT:
            case SALARY_SHIFT_FORMAT:
            case SALARY_MONTH_FORMAT: {
                return true;
            }

            default: {
                return false;
            }
        }
    }



    public static String getDayOfWeekVi(int id) {
        switch (id) {
            case Constant.MONDAY: {
                return "Thứ hai";
            }

            case Constant.TUESDAY: {
                return "Thứ ba";
            }

            case Constant.WEDNESDAY: {
                return "Thứ tư";
            }

            case Constant.THURSDAY: {
                return "Thứ năm";
            }

            case Constant.FRIDAY: {
                return "Thứ sáu";
            }

            case Constant.SATURDAY: {
                return "Thứ bảy";
            }

            case Constant.SUNDAY: {
                return "Chủ nhật";
            }

            default: {
                return "Chủ nhật";
            }
        }
    }

    public static String getPeriodOfDayVi(int id) {
        switch (id) {
            case Constant.MORNING: {
                return "Buổi sáng";
            }

            case Constant.AFTERNOON: {
                return "Buổi shiều";
            }

            case Constant.EVENING: {
                return "Buổi tối";
            }

            default: {
                return "Buổi sáng";
            }
        }
    }

    public static boolean isDayOfWeekIDValid(int id) {
        switch (id) {
            case Constant.MONDAY:
            case Constant.TUESDAY:
            case Constant.WEDNESDAY:
            case Constant.THURSDAY:
            case Constant.FRIDAY:
            case Constant.SATURDAY:
            case Constant.SUNDAY: {
                return true;
            }

            default: {
                return false;
            }
        }
    }

    public static boolean isPeriodOfDayValid(int id) {
        switch (id) {
            case Constant.MORNING:
            case Constant.AFTERNOON:
            case Constant.EVENING: {
                return true;
            }

            default: {
                return false;
            }
        }
    }

    public static boolean isStartDateVaild(Long startDate, Long endDate) {
        return startDate != null &&
                ((endDate != null && startDate < endDate) || startDate > 0);
    }

    public static boolean isEndDateVaild(Long startDate, Long endDate) {
        return endDate == null || startDate == null || endDate > startDate;
    }




    public static boolean isStringSetValid(Set<String> set) {
        return !set.contains(null) && !set.contains("");
    }

    /**
     * Remove all null and empty string of input set
     *
     * @param set the input set
     * @return true if the result set is empty, false otherwise
     */
    public static boolean removeNullAndEmptyElement(Set<String> set) {
        set.remove(null);
        set.remove("");
        return set.isEmpty();
    }



    public static boolean isNotNullAndNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNullOrNotEmpty(String value) {
        return value == null || !value.isEmpty();
    }

    public static boolean isNullOrPositive(Long value) {
        return value == null || value >= 0;
    }

    public static boolean isNullOrPositive(Integer value) {
        return value == null || value >= 0;
    }

    public static boolean isTimePeriodValid(long startTime, long endTime) {
        return startTime >= 0 && (endTime == -1 || startTime <= endTime);
    }

    public static boolean isSalaryRangeValid(double minSalary, double maxSalary) {
        return minSalary == -1 || maxSalary == -1 || (minSalary > 0 && minSalary < maxSalary);
    }


}
