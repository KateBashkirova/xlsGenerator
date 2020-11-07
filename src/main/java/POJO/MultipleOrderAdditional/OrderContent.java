package POJO.MultipleOrderAdditional;

public class OrderContent {
    private String productID;
    private Integer quantity;
    private Float pricePerUnit;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
