package org.infobip.mobile.messaging.mobileapi;

public enum InternalSdkError {
    ERROR_SAVING_MESSAGE("20000", "Error saving message"),
    ERROR_ACCESSING_HMS_SERVICES("20001", "Error accessing HMS services"),
    ERROR_TOKEN_REFRESH("20002", "Failed to complete token refresh"),
    ERROR_HMS_TOKEN_CLEANUP("20003", "Failed to complete HMS token cleanup"),
    ERROR_EMPTY_SYSTEM_DATA("20004", "System data is empty, cannot report"),
    DEVICE_NOT_SUPPORTED("20005", "Device is not supported"),
    NO_VALID_REGISTRATION("20006", "There is no valid registration"),
    DEPERSONALIZATION_IN_PROGRESS("20007", "Depersonalization is currently in progress"),
    ERROR_SAVING_EMPTY_OBJECT("20008", "Cannot save object without changes"),
    ERROR_ATTACHMENT_MAX_SIZE_EXCEEDED("20010", "Maximum allowed attachment size exceeded"),
    NETWORK_UNAVAILABLE("20009", "Network unavailable");

    private final String code;
    private final String message;

    InternalSdkError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Creates new instance of {@link MobileMessagingError} and returns its <i>toString()</i> method
     *
     * @return {@link MobileMessagingError#toString()} with specific error code and description message
     */
    public String get() {
        return new MobileMessagingError(code, message).toString();
    }

    public InternalSdkException getException() {
        return new InternalSdkException(getError().toString());
    }

    public MobileMessagingError getError() {
        return new MobileMessagingError(code, message);
    }

    public class InternalSdkException extends RuntimeException {
        InternalSdkException(String message) {
            super(message);
        }
    }
}
