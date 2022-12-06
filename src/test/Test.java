package test;

import application.Configuration;

public class Test {
	public static void test(boolean passed, String message) {
		try {
			throw new AssertionError(message);
		}
		catch (AssertionError error) {
			StackTraceElement trace = error.getStackTrace()[1];
			System.out.print(trace.getFileName());
			System.out.print(":");
			System.out.print(trace.getLineNumber());
			System.out.print(": ");
			System.out.print((passed) ? Configuration.GAME_SUCCESS+"passed"+Configuration.TEXT_RESET : Configuration.GAME_ERROR+"error"+Configuration.TEXT_RESET);
			System.out.print(": ");
			System.out.println(Configuration.GAME_INFO+message+Configuration.TEXT_RESET);
		}
	}
}
