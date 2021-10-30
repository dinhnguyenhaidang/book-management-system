package com.b1704721.bookmanager;

import com.b1704721.bookmanager.controller.BookControllerTest;
import com.b1704721.bookmanager.controller.ControllerTestSuite;
import com.b1704721.bookmanager.service.impl.BookServiceTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runs tests
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(BookServiceTest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println("Test was successful: " + result.wasSuccessful());
	}

}
