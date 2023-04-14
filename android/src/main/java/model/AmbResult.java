package model;

public class AmbResult {
    UserLocation UserLocationObject;
    VehicleLocation VehicleLocationObject;
    private boolean active;
    private String orderId;
    private String orderType;
    private String relationship;
    private String userName;
    private String mobileNo;
    private float age;
    private String profileImage;
    private String fragmentedAddress;
    private String status;
    private float payment;
    private String _id;
    ReceiverId ReceiverIdObject;
    private long pickUpDateTime;
    UserId UserIdObject;
    private int updatedAt;
    private int createdAt;
    private float __v;


    // Getter Methods

    public UserLocation getUserLocation() {
        return UserLocationObject;
    }

    public VehicleLocation getVehicleLocation() {
        return VehicleLocationObject;
    }

    public boolean getActive() {
        return active;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public float getAge() {
        return age;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getFragmentedAddress() {
        return fragmentedAddress;
    }

    public String getStatus() {
        return status;
    }

    public float getPayment() {
        return payment;
    }

    public String get_id() {
        return _id;
    }

    public ReceiverId getReceiverId() {
        return ReceiverIdObject;
    }

    public long getPickUpDateTime() {
        return pickUpDateTime;
    }

    public UserId getUserId() {
        return UserIdObject;
    }

    public float getUpdatedAt() {
        return updatedAt;
    }

    public float getCreatedAt() {
        return createdAt;
    }

    public float get__v() {
        return __v;
    }

    // Setter Methods

    public void setUserLocation(UserLocation userLocationObject) {
        this.UserLocationObject = userLocationObject;
    }

    public void setVehicleLocation(VehicleLocation vehicleLocationObject) {
        this.VehicleLocationObject = vehicleLocationObject;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setFragmentedAddress(String fragmentedAddress) {
        this.fragmentedAddress = fragmentedAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setReceiverId(ReceiverId receiverIdObject) {
        this.ReceiverIdObject = receiverIdObject;
    }

    public void setPickUpDateTime(int pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public void setUserId(UserId userIdObject) {
        this.UserIdObject = userIdObject;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public void set__v(float __v) {
        this.__v = __v;
    }
}
