package com.ag.testapplication.library.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by 4-Eyes on 05/04/2017.
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthenticationUnitTest.class, TrackApiUnitTest.class})
public class ApiTestSuite {
}
