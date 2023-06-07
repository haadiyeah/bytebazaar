package bytebazaar;

import api.shipmentAPI;

public class Shipment {
    private int shipmentID;
    private int orderID;
    private int trackID;
    private String deliverTo;
    private String address;
    private String phone;
    private String email;

    public Shipment(int orderID, String deliverTo, String address, String phone, String email) {
        this.orderID = orderID;
        this.deliverTo = deliverTo;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int Validate() {
        // API Class
        this.trackID = shipmentAPI.Validate(this.orderID);
        return trackID;
    }

    // Getters and setters
    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
