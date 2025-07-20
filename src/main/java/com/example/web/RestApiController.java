package com.example.web;

import com.example.service.RestApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RestApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    private final RestApiService restApiService;

    public RestApiController(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @GetMapping(value = {"/", "/server-info"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getRequestInfo(@RequestHeader LinkedHashMap<String, String> httpHeaders, HttpServletRequest httpServletRequest) {
        httpHeaders.put("remoteHost", httpServletRequest.getRemoteHost());
        httpHeaders.put("localAddress", httpServletRequest.getLocalAddr());
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            httpHeaders.put("hostName", localHost.getHostName());
            httpHeaders.put("hostAddress", localHost.getHostAddress());
            httpHeaders.put("canonicalHostName", localHost.getCanonicalHostName());
            httpHeaders.put("serverLocalDateTime", LocalDateTime.now().toString());
            httpHeaders.put("serverZonedDateTime", ZonedDateTime.now().toString());
            httpHeaders.put("serverOffsetDateTime", OffsetDateTime.now().toString());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return httpHeaders;
    }

    @GetMapping("/async")
    public ResponseEntity<String> getAsyncRequestInfo() {
        LOGGER.info("Task executed :: {}", Thread.currentThread());
        restApiService.doAsyncTask();
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/schedule-async")
    public ResponseEntity<String> doScheduleAsyncTask() {
        LOGGER.info("Schedule Task executed :: {}", Thread.currentThread());
        restApiService.doScheduleAsyncTask();
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/schedule-async-cancel")
    public ResponseEntity<String> cancelScheduleAsyncTask() {
        LOGGER.info("Schedule Cancel Task executed :: {}", Thread.currentThread());
        restApiService.cancelScheduleAsyncTask();
        return ResponseEntity.ok().body("SUCCESS");
    }
}
