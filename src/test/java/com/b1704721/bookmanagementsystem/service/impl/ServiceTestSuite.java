package com.b1704721.bookmanagementsystem.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Groups all service tests
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ BookServiceTest.class, AuthorServiceTest.class })
public class ServiceTestSuite {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of ServiceTestSuite.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of ServiceTestSuite.");
    }

}
