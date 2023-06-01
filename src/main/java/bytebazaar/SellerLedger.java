package bytebazaar;

import java.util.LinkedList;

public class SellerLedger {
    private LinkedList<Seller> sellerAccounts;

    public SellerLedger() {
        sellerAccounts = new LinkedList<Seller>();
    }

    public LinkedList<Order> getOrdersOf(int sellerID) {
        //This condition must be met if he is logged in.
        if(getSeller(sellerID) !=null ) { //Indicates already present in ledger
            return getSeller(sellerID).getOrders();
        } else { //Find in db
            return null;
        }

    }

    //The belwo function returns the index of the seller if currently present in ledger, else returns -1
    public int checkInLedger(String email, String password) {
        for (int i = 0; i < sellerAccounts.size(); i++) {
            if (sellerAccounts.get(i).getEmail().equals(email) && sellerAccounts.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public int loginRequest(String email, String password) {
        //Check if the account is already present in the ledger
        int check=checkInLedger(email, password);
        if (check!=-1) {
            //sellerAccounts.addFirst (sellerAccounts.remove(check));
            return sellerAccounts.get(check).getID();
        }

        Seller s = DBHandler.getInstance().getSeller(email, password);
        if (s != null) {
            s.setDetails();// will call the seller's setdetails func, to add order-recieved log and
                           // product catalog
            sellerAccounts.add(s);
            return s.getID();
        } else {
            System.out.println("authenticate login returned null for seller");
            return -1;
        }
    }
   
    // public boolean updateCurrentSeller(String name, String email, String password, String phone, String address) {
    //     if (DBHandler.getInstance().updateBuyer(sellerAccounts.get(0).getID(), name, email, password, phone, address)) {
    //         sellerAccounts.get(0).setName(name);
    //         sellerAccounts.get(0).setEmail(email);
    //         sellerAccounts.get(0).setPassword(password);
    //         sellerAccounts.get(0).setPhoneNum(phone.toString());
    //         //sellerAccounts.get(0).setDeliveryDetails(address);
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

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
