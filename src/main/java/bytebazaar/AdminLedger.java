package bytebazaar;

import java.util.LinkedList;

public class AdminLedger {
    private LinkedList<Admin> adminAccounts;

    public AdminLedger() {
        adminAccounts=new LinkedList<Admin>();
    }


    public int findInLedger(String email, String password) {
        for (int i = 0; i < adminAccounts.size(); i++) {
            if (adminAccounts.get(i).getEmail().equals(email) && adminAccounts.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public int loginRequest(String email, String password) {
        int check=findInLedger(email, password);
        if (check!=-1) {
            //adminAccounts.addFirst (adminAccounts.remove(check));
            return adminAccounts.get(check).getID();
        }

        Admin a = DBHandler.getInstance().authenticateAdminLogin(email, password);
        if (a != null) {
            //a.setDetails();
            adminAccounts.addFirst(a);
            return a.getID();
        } else {
            return -1;
        }
    }

    // public boolean updateCurrentAdmin(String name, String email, String password, String phone, String address) {
    //     if (DBHandler.getInstance().updateAdmin(adminAccounts.get(0).getID(), name, email, password, phone, address)) {
    //         adminAccounts.get(0).setName(name);
    //         adminAccounts.get(0).setEmail(email);
    //         adminAccounts.get(0).setPassword(password);
    //         adminAccounts.get(0).setPhoneNum("" + phone);
    //         //adminAccounts.get(0).setDeliveryDetails(address);
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    public boolean deleteAdmin(int adminID){
        boolean ret= DBHandler.getInstance().deleteUser(adminID);
        adminAccounts.remove(getAdmin(adminID));
        return ret;
    }

    // public void setCurrentAdmin(Admin currentAdmin) {
    //     //adminAccounts.add(currentAdmin);
    // }

    //Find admin with the given ID and return its object
    public Admin getAdmin(int ID) {
        for(int i=0;i<adminAccounts.size();i++) {
            if(adminAccounts.get(i).getID() == ID) {
                return adminAccounts.get(i);
            }
        }
        return null;
    }



    
}
