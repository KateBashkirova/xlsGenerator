package mainFunctions;

public class PriceCounter {
    public static float getTotalPrice(Integer productAmount, Float pricePerUnit, String courierDelivery) {
        return (!courierDelivery.equals("yes")) ? productAmount*pricePerUnit : productAmount*pricePerUnit+500;
    }
}
