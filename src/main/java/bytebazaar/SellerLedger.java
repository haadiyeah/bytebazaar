package bytebazaar;

import java.util.LinkedList;

public class SellerLedger {
    private LinkedList<Seller> sellerAccounts;

    public SellerLedger() {
        sellerAccounts = new LinkedList<Seller>();
    }

    public int checkInLedger(String email, String password) {
        for (int i = 0; i < sellerAccounts.size(); i++) {
            if (sellerAccounts.get(i).getEmail().equals(email) && sellerAccounts.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public boolean loginRequest(String email, String password) {
        int check=checkInLedger(email, password);
        if (check!=-1) {
            //Set current seller as the one who returned
            //TODO After fixing this and adding the get-through id func, this line should be removed
            sellerAccounts.addFirst (sellerAccounts.remove(check));
        }

        Seller s = DBHandler.getInstance().authenticateSellerLogin(email, password);
        if (s != null) {
            s.setDetails();// will call the seller's setdetails func, to add order-recieved log and
                           // product catalog
            sellerAccounts.add(s);
            return true;
        } else {
            System.out.println("authenticate login returned null for seller");
            return false;
        }
    }

    public Seller getCurrentSeller() {
        return sellerAccounts.getFirst();
    }

   
    public boolean updateCurrentSeller(String name, String email, String password, String phone, String address) {
        if (DBHandler.getInstance().updateBuyer(sellerAccounts.get(0).getID(), name, email, password, phone, address)) {
            sellerAccounts.get(0).setName(name);
            sellerAccounts.get(0).setEmail(email);
            sellerAccounts.get(0).setPassword(password);
            sellerAccounts.get(0).setPhoneNum(phone.toString());
            //sellerAccounts.get(0).setDeliveryDetails(address);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteSeller(int sellerID){
        boolean ret= DBHandler.getInstance().deleteUser(sellerID);
        sellerAccounts.remove(getSeller(sellerID));
        return ret;
    }

    // public void setCurrentSeller(Seller currentSeller) {
    //     //sellerAccounts.add(currentSeller);
    // }

    //Find seller with the given ID and return its object
    public Seller getSeller(int ID) {
        for(int i=0;i<sellerAccounts.size();i++) {
            if(sellerAccounts.get(i).getID() == ID) {
                return sellerAccounts.get(i);
            }
        }
        return null;
    }

}
