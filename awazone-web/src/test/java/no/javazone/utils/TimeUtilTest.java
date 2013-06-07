package no.javazone.utils;

import org.joda.time.DateTime;
import org.junit.Test;

public class TimeUtilTest {
	@Test
	public void skal() {
		doit(DateTime.now().minusYears(5));
		doit(DateTime.now().minusMonths(5));
		doit(DateTime.now().minusHours(5));
		doit(DateTime.now().minusMinutes(5));
		doit(DateTime.now().minusSeconds(5));
		doit(DateTime.now().minusMillis(5));
		doit(DateTime.now().plusMinutes(5));

		doit(DateTime.now().minusYears(5).minusMonths(10));
		doit(DateTime.now().minusHours(5).plusMinutes(10));
		doit(DateTime.now().minusHours(5).plusMinutes(10).minusYears(21));
	}

	@Test
	public void skal2() {
		System.out.println(TimeUtil.generateRelativeDate(1368370253000L));
	}

	private void doit(final DateTime dateTime) {
		System.out.println(TimeUtil.generateRelativeDate(dateTime.getMillis()));
	}
}
