package edu.sjsu.seekers.silversnug.request;

import java.io.Serializable;

public class AddressBookRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String addressId;
    private String addressName;
    private String latitude;
    private String longitude;
    private String address;

    public AddressBookRequest()
    {

    }

    public AddressBookRequest(String userId, String addressId, String addressName, String latitude, String longitude, String address) {
        this.userId = userId;
        this.addressId = addressId;
        this.addressName = addressName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressBookRequest{" +
                "userId='" + userId + '\'' +
                ", addressId='" + addressId + '\'' +
                ", addressName='" + addressName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
