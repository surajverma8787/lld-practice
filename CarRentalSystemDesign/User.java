
public class User {
    private String userID;
    private String drivingLicenseNum;
    private String userName;

    public User(String userID, String drivingLicenseNum, String userName) {
        this.userID = userID;
        this.drivingLicenseNum = drivingLicenseNum;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getDrivingLicenseNum() {
        return drivingLicenseNum;
    }

    public String getUserName() {
        return userName;
    }
}