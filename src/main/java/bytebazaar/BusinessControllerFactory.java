package bytebazaar;

//A class that contains singleton instances of different business controllers.

public class BusinessControllerFactory {
    private static adminController adminController=null;
    private static BuyerController buyerController=null;
    private static SellerController sellerController=null;

    private BusinessControllerFactory(){
    }

    public static adminController getAdminControllerInst(){
        if(adminController==null) 
            adminController = new adminController();

        return adminController;
    }

    public static BuyerController getBuyerControllerInst() {
        if(buyerController==null) {
            buyerController=new BuyerController();
        }
        
        return buyerController;
    }

    public static SellerController getSellerControllerInst() {
        if(sellerController==null) {
            sellerController=new SellerController();
        }
        return sellerController;
    }
    
}
