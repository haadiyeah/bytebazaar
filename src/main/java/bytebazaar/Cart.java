package bytebazaar;

import java.util.LinkedList;

public class Cart {
    LinkedList<SalesLineItem> itemsList;
    float runningTotal;

    public Cart(){
        itemsList=new LinkedList<SalesLineItem>();
        runningTotal=0;
    }

    public Cart(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
        runningTotal=0;
    }
    public LinkedList<SalesLineItem> getItemsList() {
        return itemsList;
    }
    public void setItemsList(LinkedList<SalesLineItem> itemsList) {
        this.itemsList = itemsList;
    }

    //Calc running total function
    
}
