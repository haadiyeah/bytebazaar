package bytebazaar;

import java.util.LinkedList;

public class Cart {
    LinkedList<SalesLineItem> itemsList;
    float runningTotal;

    public Cart(){
        itemsList=new LinkedList<SalesLineItem>();
        runningTotal=0;
    }

    public void calculateTotal() {
        runningTotal=0;
        for(int i=0;i<itemsList.size();i++) {
            runningTotal+= itemsList.get(i).getPrice();
        }
    }

    public Cart(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
        calculateTotal();;
    }
    public LinkedList<SalesLineItem> getItemsList() {
        return itemsList;
    }
    public void setItemsList(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
        calculateTotal();
    }

    public void add(SalesLineItem p) {
        boolean flag;
        for(int i=0;i<itemsList.size();i++) {
            //If exists in the list, just update its quantity
            if (itemsList.get(i).getProductID() == p.getProductID()) {
                itemsList.get(i).setQuantity(itemsList.get(i).getQuantity() +1);
                return;
            }
        }
       
        //Doesnt exist in the list==add it
        itemsList.add(p);
    }

    //Calc running total function
    
}
