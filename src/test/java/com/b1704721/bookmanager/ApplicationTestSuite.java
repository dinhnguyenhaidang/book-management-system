package com.b1704721.bookmanager;

import com.b1704721.bookmanager.controller.ControllerTestSuite;
import com.b1704721.bookmanager.converter.ConverterTestSuite;
import com.b1704721.bookmanager.service.impl.ServiceTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Groups all test suites
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ControllerTestSuite.class, ConverterTestSuite.class, ServiceTestSuite.class })
public class ApplicationTestSuite {

}
