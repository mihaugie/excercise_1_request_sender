package com.gailitis.epam_excercise_2_apps_request_sender.controller;

import com.gailitis.epam_excercise_2_apps_request_sender.service.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private RestService restService;

    public MessageController(RestService restService) {
        this.restService = restService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity forwardMessage(@RequestBody String message) {
        ResponseEntity responseEntity = restService.postMessage(message);
        return responseEntity;

    }

}
