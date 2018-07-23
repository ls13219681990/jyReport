package com.common;

//public class DateConverter extends StrutsTypeConverter {
public class DateConverter {
    private static String DEFAULT_DATE_TIME_FORMART = "yyyy-MM-dd HH:mm:ss";

/*	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Timestamp ts = null;
		String dateString = null;
		if (values != null && values.length > 0) {
			dateString = values[0];
			if (dateString != null) {
				if (dateString.length() == 10) {
					dateString += " 00:00:00";
				}
				ts = Timestamp.valueOf(dateString);
			}
		}
		return ts;
	}

	@Override
	public String convertToString(Map context, Object o) {
		Date date = (Date) o;
		String dateTimeString = DateFormatUtils.format(date,
				DEFAULT_DATE_TIME_FORMART);
		return dateTimeString;
	}*/

}
