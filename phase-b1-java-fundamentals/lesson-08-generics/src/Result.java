
public class Result<T> {

    private final boolean success;
    private final T data;
    private final String error;

    // Private constructor
    private Result(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    // Factory method for success
    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null);
    }

    // Factory method for failure
    public static <T> Result<T> failure(String error) {
        return new Result<>(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        if (success) {
            return "Success: " + data;
        }

        return "Failure: " + error;
    }
}
