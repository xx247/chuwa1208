package PackageTracking;
//- Create `Package` class:
//        - `private String trackingId`
//        - `private DeliveryStatus status`
//        - `public void updateStatus(DeliveryStatus newStatus)` - validate transition before updating
public class Package {
    private String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId, DeliveryStatus status){
        this.trackingId = trackingId;
        this.status = status;
    }
    public void updateStatus(DeliveryStatus newStatus){
        if(!status.canTransitionTo(newStatus)){
            throw new TransitionException("Invalid transition: "+status.getDescription()+"->"+ newStatus.getDescription());
        }

        this.status = newStatus;
        System.out.println("transit success to "+ newStatus);
    }
}
