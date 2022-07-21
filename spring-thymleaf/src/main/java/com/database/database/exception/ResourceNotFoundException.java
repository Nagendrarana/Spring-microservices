package com.database.database.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class ResourceNotFoundException  extends Exception{
    @Serial
    private static final long serialVersionUID = -9079454849611061074L;
    private static final Logger log = LoggerFactory.getLogger(ResourceNotFoundException.class);
    public ResourceNotFoundException() {

        super();
        log.info("Error Handled");
    }

    public ResourceNotFoundException(final String message) {
        super(message);
        log.info("Error Handled");
    }
}
