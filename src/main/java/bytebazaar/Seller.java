package bytebazaar;

public class Seller extends User{
    private String storeInformation;

    public Seller(String email, String password, String phoneNum, String name) {
        super(email, password, phoneNum, name);
    }
    public Seller(int id, String email, String password, String phoneNum, String name) {
        super(id, email, password, phoneNum, name);
    }
    
    public String getStoreInformation() {
        return storeInformation;
    }

    public void setStoreInformation(String storeInformation) {
        this.storeInformation = storeInformation;
    }

    
}
