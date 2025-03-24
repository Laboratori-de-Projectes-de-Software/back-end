package developers.iasuperleague.exception;

public class NotFoundServiceException extends RuntimeException {
    public NotFoundServiceException(String message) {
        super(message);
    }
}
