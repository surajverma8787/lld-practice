import java.util.*;

public class User {
    private int userID;
    private String drivingLicenseNum;
    private String userName;

    public User(int userID, String drivingLicenseNum, String userName) {
        this.userID = userID;
        this.drivingLicenseNum = drivingLicenseNum;
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getDrivingLicenseNum() {
        return drivingLicenseNum;
    }

    public String getUserName() {
        return userName;
    }
}