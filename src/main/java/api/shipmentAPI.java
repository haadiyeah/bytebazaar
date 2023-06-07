package api;

import java.util.Random;

import bytebazaar.DBHandler;

public class shipmentAPI {

    public static int Validate(int S) {
        return DBHandler.getInstance().shipmentAPI(S);
    }

    public static int findOrderStatus(int S) {
        Random random = new Random();
        return random.nextInt(4) + 1;
    }
}
