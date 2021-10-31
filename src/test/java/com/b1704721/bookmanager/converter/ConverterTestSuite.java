package com.b1704721.bookmanager.converter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Groups all converter tests
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ BookConverterTest.class, AuthorConverterTest.class })
public class ConverterTestSuite {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of ConverterTestSuite.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of ConverterTestSuite.");
    }

}
