package bytebazaar;

public class ShipmentAPI {

    public int Validate(Shipment S) {
        return DBHandler.getInstance().shipmentAPI(S.getOrderID());
    }

}
