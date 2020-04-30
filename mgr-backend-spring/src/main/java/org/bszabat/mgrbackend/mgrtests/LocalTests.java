package org.bszabat.mgrbackend.mgrtests;

import org.bszabat.mgrbackend.service.IOBlockingService;

public class LocalTests {

    public static void main(String[] args) {
        IOBlockingService ioBlockingService = new IOBlockingService();
        ioBlockingService.callsToDbLastsEndpoint(URLHelper.DB_OPERATION_WAIT_FOR);
    }
}
