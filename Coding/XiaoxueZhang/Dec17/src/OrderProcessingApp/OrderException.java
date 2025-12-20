//- Create custom exception `OrderException` extending `RuntimeException`:
//        - `private IErrorCode errorCode`
//        - Constructor accepting `IErrorCode`
//        - `public IErrorCode getErrorCode()`


package OrderProcessingApp;

public class OrderException extends RuntimeException {

    private IErrorCode errorCode;

    public OrderException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return errorCode.getCode();
    }
}
