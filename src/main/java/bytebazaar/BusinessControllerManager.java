package bytebazaar;

//A class that contains singleton instances of different business controllers.

public class BusinessControllerManager {
    private static BusinessControllerManager instance=null;
    private adminController adminController=null;
    private BuyerController buyerController=null;
    private SellerController sellerController=null;

    private BusinessControllerManager(){
        adminController = new adminController();
        buyerController=new BuyerController();
        sellerController=new SellerController();
    }

    public static adminController getAdminControllerInst(){
        if (instance == null) {
            synchronized (BusinessControllerManager.class) {
                if (instance == null) {
                    instance = new BusinessControllerManager();
                }
            }
        }
        return instance.adminController;
    }

    public static BuyerController getBuyerControllerInst() {
        if (instance == null) {
            synchronized (BusinessControllerManager.class) {
                if (instance == null) {
                    instance = new BusinessControllerManager();
                }
            }
        }
        return instance.buyerController;
    }

    public static SellerController getSellerControllerInst() {
        if (instance == null) {
            synchronized (BusinessControllerManager.class) {
                if (instance == null) {
                    instance = new BusinessControllerManager();
                }
            }
        }
        return instance.sellerController;
    }
}
