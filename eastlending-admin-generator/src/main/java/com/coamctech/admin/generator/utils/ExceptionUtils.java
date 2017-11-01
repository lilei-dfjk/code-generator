package com.coamctech.admin.generator.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String getStackTraceString(Throwable throwObject) {
		StringWriter out = new StringWriter();
		throwObject.printStackTrace(new PrintWriter(out));
		return out.toString();
	}
}
