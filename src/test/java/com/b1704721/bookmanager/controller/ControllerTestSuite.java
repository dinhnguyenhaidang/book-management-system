package com.b1704721.bookmanager.controller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Groups all controller tests
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ AuthorControllerTest.class, BookControllerTest.class })
public class ControllerTestSuite {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("\nStart of ControllerTestSuite.\n");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("\nEnd of ControllerTestSuite.");
    }

}
