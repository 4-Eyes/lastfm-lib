package com.ag.testapplication.library.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by 4-Eyes on 05/04/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthenticationUnitTest.class, TrackApiUnitTest.class, AlbumApiUnitTest.class,
        ArtistApiUnitTest.class, ChartApiUnitTest.class, GeoApiUnitTest.class, LibraryApiUnitTest.class, TagApiUnitTest.class,
        UserApiUnitTest.class})
public class ApiTestSuite {
}
