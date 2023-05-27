package bytebazaar;

import java.util.LinkedList;

public class Cart {
    LinkedList<SalesLineItem> itemsList;
    float runningTotal;

    public Cart(){
        itemsList=new LinkedList<SalesLineItem>();
        runningTotal=0;
    }

    public float updateTotal() {
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

    public Cart(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
        updateTotal();
    }
    public LinkedList<SalesLineItem> getItemsList() {
        return itemsList;
    }
    public void setItemsList(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
        updateTotal();
    }

    public void add(SalesLineItem p) {
        for(int i=0;i<itemsList.size();i++) {
            //If exists in the list, just update its quantity
            if (itemsList.get(i).getProductID() == p.getProductID()) {
                itemsList.get(i).setQuantity(itemsList.get(i).getQuantity() +1);
                return;
            }
        }
       
        //Doesnt exist in the list==add it
        itemsList.add(p);
        updateTotal();
    }

    public float getRunningTotal() {
        return runningTotal;
    }

    public void updateItemQty(int indexNo, char type) {
        if(type=='+') {
            itemsList.get(indexNo).setQuantity(itemsList.get(indexNo).getQuantity()+1);
        } else if(type=='-') {
            itemsList.get(indexNo).setQuantity(itemsList.get(indexNo).getQuantity()-1);
        } else {
            return;
        }
        updateTotal();
    }


    //Calc running total function
    
}
