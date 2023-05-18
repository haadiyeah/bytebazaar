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

    //Calc running total function
    
}
