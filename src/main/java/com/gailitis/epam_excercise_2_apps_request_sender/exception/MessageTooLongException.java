package com.gailitis.epam_excercise_2_apps_request_sender.exception;

public class MessageTooLongException extends RuntimeException {

    public MessageTooLongException(String message) {
        super(message);
    }
}
