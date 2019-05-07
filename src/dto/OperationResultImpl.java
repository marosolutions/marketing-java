package dto;

public class OperationResultImpl<T> implements OperationResult<T> {
    private String _errorMessage;
    private Exception _exception;
    private T _data;

    public OperationResultImpl(T data, Exception e, String errMsg) {
        if (errMsg != "") {
            _errorMessage = errMsg;
        }
        if (e != null) {
            _exception = e;
        }
        _data = data;
    }

    public boolean isSuccess(){
        return _errorMessage == "";
    }

    public T getData() {
        return _data;
    }

    public String getErrorMessage() {
        return _errorMessage;
    }

    public Exception getException() {
        return _exception;
    }
}