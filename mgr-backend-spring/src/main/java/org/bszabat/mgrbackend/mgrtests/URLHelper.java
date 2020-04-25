package org.bszabat.mgrbackend.mgrtests;

public final class URLHelper {

    public final static String MOCK_URL = "http://localhost:1080/api";
    public final static String ALBUM_TEST_10 = MOCK_URL + "/albums/10";
    public final static String PHOTOS_TEST_5000 = MOCK_URL + "/photos/5000";
    public final static String PHOTOS_TEST_10000 = MOCK_URL + "/photos/10000";
    public final static String PHOTOS_TEST_40000 = MOCK_URL + "/photos/40000";

    public final static String DB_OPERATION_WAIT_FOR = "http://localhost:8080/api/database-operations/operation-lasts";
}
