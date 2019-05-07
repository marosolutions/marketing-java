package dto;

public interface OperationResult<T> {
    String getErrorMessage();
    boolean isSuccess();
    Exception getException();
    T getData();
}