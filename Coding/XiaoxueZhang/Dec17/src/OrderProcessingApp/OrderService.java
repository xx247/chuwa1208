//        - Create `OrderService` class with method:
//        - `public void processOrder(String orderId, int quantity)`
//        - Throw appropriate `OrderException` based on conditions

package OrderProcessingApp;

public class OrderService {
    public void processOrder(String orderId, int quantity){
        if(orderId==null||orderId.isEmpty()){
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }
        if(quantity>50){
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }
        if("CANCELLED".equals(orderId)){
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }
        System.out.println("Order processed successfully: "+orderId);
    }
}
