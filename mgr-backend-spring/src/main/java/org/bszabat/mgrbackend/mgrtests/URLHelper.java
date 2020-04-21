package org.bszabat.mgrbackend.mgrtests;

public final class URLHelper {

    public final static String PHOTOS_TEST = "http://localhost:1080/api/photos";
    public final static String PHOTOS_TEST_5000 = PHOTOS_TEST + "/5000";
    public final static String PHOTOS_TEST_10000 = PHOTOS_TEST + "/10000";
    public final static String PHOTOS_TEST_40000 = PHOTOS_TEST + "/40000";

    public final static String DB_OPERATION_WAIT_FOR = "http://localhost:3000/api/database-operations/operation-lasts";
}
