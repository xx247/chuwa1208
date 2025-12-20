//        - Create an enum `OrderErrorCode` implementing `IErrorCode`:
//        - `ORDER_NOT_FOUND(1001, "Order not found")`
//        - `ORDER_CANCELLED(1002, "Order already cancelled")`
//        - `INSUFFICIENT_STOCK(1003, "Insufficient stock")`
//        - Include private constructor and override interface methods

package OrderProcessingApp;

public enum OrderErrorCode implements IErrorCode{
    ORDER_NOT_FOUND(1001, "Order not found"),
    ORDER_CANCELLED(1002, "Order already cancelled"),
    INSUFFICIENT_STOCK(1003, "Insufficient stock");

    private final long code;
    private final String message;

    private OrderErrorCode(long code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
