package mainFunctions;

public class PriceCounter {
    public static float getTotalPrice(Integer productAmount, Float pricePerUnit, String courierDelivery) {
        float totalPrice = (!courierDelivery.equals("yes")) ? productAmount*pricePerUnit : productAmount*pricePerUnit+500;
        return totalPrice;
    }
}
