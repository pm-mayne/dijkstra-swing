package backend.model.api;


public class Result {

    private final String message;
    private final boolean isError;

    public Result(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public boolean isError() {
        return isError;
    }

    public String getMessage() {
        return message;
    }
}
