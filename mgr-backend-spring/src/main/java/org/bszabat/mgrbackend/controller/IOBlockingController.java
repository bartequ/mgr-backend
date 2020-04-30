package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.helpers.URLHelper;
import org.bszabat.mgrbackend.service.IOBlockingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IOBlockingController {

    private IOBlockingService ioBlockingService;

    public IOBlockingController(IOBlockingService ioBlockingService) {
        this.ioBlockingService = ioBlockingService;
    }

    @GetMapping("/ioblocking")
    public String performSyncApiCalls() {
        return ioBlockingService.callsToDbLastsEndpointTime(URLHelper.DB_OPERATION_WAIT_FOR);
    }
}
