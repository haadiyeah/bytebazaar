package bytebazaar;

//A class that contains singleton instances of different business controllers.

public class BusinessControllerFactory {
    private static adminController admincontroller=null;
    private static BuyerController buyercontroller=null;
    private static LoginBController logincontroller=null;

    private BusinessControllerFactory(){

    }

    public static adminController getAdminControllerInst(){
        if(admincontroller==null) 
            admincontroller = new adminController();

        return admincontroller;
    }

    public static BuyerController getBuyerControllerInst() {
        if(buyercontroller==null) {
            buyercontroller=new BuyerController();
        }
        if(buyercontroller.getCurrentUser() == null)
            System.out.println("\n\n*Getting buyer controller instance, current user is NULL\n\n");
        return buyercontroller;
    }

    public static LoginBController getLoginControllerInst() {
        if(logincontroller==null) {
            logincontroller=new LoginBController();
        }
        return logincontroller;
    }
    
}
