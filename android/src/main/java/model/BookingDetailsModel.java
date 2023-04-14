package model;

import java.util.ArrayList;


public class BookingDetailsModel {
    private String message;
    private float code;
    AmbData data;


    // Getter Methods

    public String getMessage() {
        return message;
    }

    public float getCode() {
        return code;
    }

    public AmbData getData() {
        return data;
    }

    // Setter Methods

    public void setMessage( String message ) {
        this.message = message;
    }

    public void setCode( float code ) {
        this.code = code;
    }

    public void setData( AmbData dataObject ) {
        this.data = dataObject;
    }
}

class AmbulanceUserId {
    private String fname;
    private String lname;
    private String email;
    private String mobileNo;
    private String userType;
    private String profileImage;
    private String city;
    private String state;
    private String _id;


    // Getter Methods

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getUserType() {
        return userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String get_id() {
        return _id;
    }

    // Setter Methods

    public void setFname( String fname ) {
        this.fname = fname;
    }

    public void setLname( String lname ) {
        this.lname = lname;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setMobileNo( String mobileNo ) {
        this.mobileNo = mobileNo;
    }

    public void setUserType( String userType ) {
        this.userType = userType;
    }

    public void setProfileImage( String profileImage ) {
        this.profileImage = profileImage;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public void set_id( String _id ) {
        this._id = _id;
    }
}
class ReceiverId {
    Location LocationObject;
    private boolean isOnline;
    private String hospitalName;
    private String upiId;
    private boolean active;
    ArrayList<Object> images = new ArrayList<Object>();
    private String description;
    private String vehicleNo;
    ArrayList<Object> vehicleDocs = new ArrayList<Object>();
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String _id;
    private String township;
    private String fragmentedAddress;
    AmbulanceUserId userId;
    private float updatedAt;
    private float createdAt;
    private float __v;


    // Getter Methods

    public Location getLocation() {
        return LocationObject;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getUpiId() {
        return upiId;
    }

    public boolean getActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }

    public String get_id() {
        return _id;
    }

    public String getTownship() {
        return township;
    }

    public String getFragmentedAddress() {
        return fragmentedAddress;
    }

    public AmbulanceUserId getUserId() {
        return userId;
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

    public void setLocation( Location locationObject ) {
        this.LocationObject = locationObject;
    }

    public void setIsOnline( boolean isOnline ) {
        this.isOnline = isOnline;
    }

    public void setHospitalName( String hospitalName ) {
        this.hospitalName = hospitalName;
    }

    public void setUpiId( String upiId ) {
        this.upiId = upiId;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setVehicleNo( String vehicleNo ) {
        this.vehicleNo = vehicleNo;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public void setPincode( String pincode ) {
        this.pincode = pincode;
    }

    public void set_id( String _id ) {
        this._id = _id;
    }

    public void setTownship( String township ) {
        this.township = township;
    }

    public void setFragmentedAddress( String fragmentedAddress ) {
        this.fragmentedAddress = fragmentedAddress;
    }

    public void setUserId( AmbulanceUserId userIdObject ) {
        this.userId = userIdObject;
    }

    public void setUpdatedAt( float updatedAt ) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt( float createdAt ) {
        this.createdAt = createdAt;
    }

    public void set__v( float __v ) {
        this.__v = __v;
    }
}
 class UserId {
    private String fname;
    private String lname;
    private String mobileNo;
    private String userType;
    private String profileImage;
    private String _id;


    // Getter Methods

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getUserType() {
        return userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String get_id() {
        return _id;
    }

    // Setter Methods

    public void setFname( String fname ) {
        this.fname = fname;
    }

    public void setLname( String lname ) {
        this.lname = lname;
    }

    public void setMobileNo( String mobileNo ) {
        this.mobileNo = mobileNo;
    }

    public void setUserType( String userType ) {
        this.userType = userType;
    }

    public void setProfileImage( String profileImage ) {
        this.profileImage = profileImage;
    }

    public void set_id( String _id ) {
        this._id = _id;
    }
}
