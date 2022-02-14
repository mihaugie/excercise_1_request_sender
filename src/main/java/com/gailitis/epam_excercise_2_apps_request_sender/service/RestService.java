package com.gailitis.epam_excercise_2_apps_request_sender.service;

import com.gailitis.epam_excercise_2_apps_request_sender.exception.EmptyMessageException;
import com.gailitis.epam_excercise_2_apps_request_sender.exception.MessageTooLongException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    private final String POST_KEY = "messageContent";
    private final String BACKEND_URL = "http://localhost:8091/message";
    private final int MESSAGE_LIMIT = 256;

    public RestService() {
    }

    public ResponseEntity postMessage(String message) {
        validateMessageLength(message);

        HttpHeaders headers = new HttpHeaders();

        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put(POST_KEY, message);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(BACKEND_URL, entity, String.class);

        logResult(response);
        return response;
    }

    private void validateMessageLength(String message) {
        if (message.isEmpty()) {
            throw new EmptyMessageException("Message must not be empty");
        }

        if (message.length() >= MESSAGE_LIMIT) {
            throw new MessageTooLongException("The message must be shorter than " + MESSAGE_LIMIT + " characters");
        }
    }

    private void logResult(ResponseEntity<String> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
        } else {
            System.out.println("Request Failed");
        }
    }

}
