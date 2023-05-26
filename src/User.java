

public class User {
    private int Id;
    private String userName;
    private String issued;
    private String validUntil;

    public User(int Id, String userName, String issued, String validUntil) {
        this.Id = Id;
        this.userName = userName;
        this.issued = issued;
        this.validUntil = validUntil;
    }


    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIssued() {
        return this.issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getValidUntil() {
        return this.validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }


}