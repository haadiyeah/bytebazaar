package bytebazaar;

import java.util.LinkedList;

public class Cart {
    private LinkedList<SalesLineItem> itemsList;
    private float runningTotal;

    //Constructor for creating an empty cart.
    public Cart(){
        itemsList=new LinkedList<SalesLineItem>();
        runningTotal=0;
    }
    //Constructor for creating cart, given a list of items.
    public Cart(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
        updateTotal();
    }


    private float updateTotal() {
        runningTotal=0;
        for(int i=0;i<itemsList.size();i++) {
            runningTotal+= itemsList.get(i).getSubTotal();
        }
        return runningTotal;
    }

    public void clearCart() {
        itemsList.clear();
        updateTotal(); //makes it 0
    }

    public void addToCart(Product p) {
        //itemsList.add(new SalesLineItem(p));
        for(int i=0;i<itemsList.size();i++) {
            //If exists in the list, just update its quantity
            if (itemsList.get(i).getProductID() == p.getProductID()) {
                itemsList.get(i).setQuantity(itemsList.get(i).getQuantity() +1);
                return;
            }
        }
        //Doesnt exist in the list==add it
        itemsList.add(new SalesLineItem(p));
        updateTotal();
    }

    public LinkedList<SalesLineItem> getItemsList() {
        return itemsList;
    }

    // public void add(SalesLineItem p) {
    //     for(int i=0;i<itemsList.size();i++) {
    //         //If exists in the list, just update its quantity
    //         if (itemsList.get(i).getProductID() == p.getProductID()) {
    //             itemsList.get(i).setQuantity(itemsList.get(i).getQuantity() +1);
    //             return;
    //         }
    //     }
    //     //Doesnt exist in the list==add it
    //     itemsList.add(p);
    //     updateTotal();
    // }

    public float getRunningTotal() {
        return runningTotal;
    }

    public boolean updateItemQty(int productid, char type) {
        boolean returnStatus=true;
        int indexNo= getProductIndexByProductID(productid);
        if(indexNo==-1) {
            //Product does not exist in the cart
            return false;
        }
        if(type=='+') {
            itemsList.get(indexNo).setQuantity(itemsList.get(indexNo).getQuantity()+1);
        } else if(type=='-') {
            itemsList.get(indexNo).setQuantity(itemsList.get(indexNo).getQuantity()-1);
            if(itemsList.get(indexNo).getQuantity() == 0) {
                itemsList.remove(indexNo);
                returnStatus= false;
            }
        } else {
            //The type of update is not + or -
            return false;
        }
        updateTotal();
        return returnStatus;
        
    }

    private int getProductIndexByProductID(int productid) {
        for(int i=0;i< itemsList.size();i++) {
            if(itemsList.get(i).getProductID() == productid) {
                return i;
            }
        }
        return -1;
    }

    //Calc running total function
    
}
