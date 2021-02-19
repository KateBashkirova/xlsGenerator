package POJO.MultipleOrderAdditional;

public class OrderContent {
    public String productID;
    public String productName;
    public Integer quantity;
    public Float pricePerUnit;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public String toString() {
        // разделитель в виде пробела не подойдёт, поскольку в названии товара может быть пробел
//        return productID + ';' + productName + ';' + quantity + ';' + pricePerUnit + ';';
        return productID + ';' + productName + ';' + quantity + ';' + pricePerUnit + '\n';
    }
}
