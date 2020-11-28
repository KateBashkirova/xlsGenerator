package POJO.MultipleOrderAdditional;

public class CustomerAddress {
    private String country;
    private String city;
    private String houseAddress;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    @Override
    public String toString() {
        // разделитель в виде пробела не подойдёт, поскольку в названии товара может быть пробел
        return country + ';' + city + ';' + houseAddress;
    }
}
