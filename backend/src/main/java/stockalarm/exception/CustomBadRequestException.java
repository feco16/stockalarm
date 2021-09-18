package stockalarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Temporary exception class.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException(final String message) {
        super(message);
    }

    public CustomBadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
