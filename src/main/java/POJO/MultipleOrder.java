package POJO;


import POJO.MultipleOrderAdditional.ClientInfo;
import POJO.MultipleOrderAdditional.CustomerAddress;
import POJO.MultipleOrderAdditional.OrderContent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MultipleOrder {
    public Integer orderID;
    @JsonProperty("content")
    public List<OrderContent> contentList;
    @JsonProperty("clientInfo")
    public List<ClientInfo> clientInfoList;
    @JsonProperty("address")
    public List<CustomerAddress> addressList;


    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public List<ClientInfo> getClientInfoList() {
        return clientInfoList;
    }

    public ArrayList<String> getContentList() {
        ArrayList<String> fullOrderContent = new ArrayList<>();
        for (OrderContent orderContent : contentList) {
            assert false;
            fullOrderContent.add(orderContent.getProductID());
            fullOrderContent.add(orderContent.getProductName());
            fullOrderContent.add(String.valueOf(orderContent.getQuantity()));
            fullOrderContent.add(String.valueOf(orderContent.getPricePerUnit()));
        }
        return fullOrderContent;
    }

    public void setContentList(List<OrderContent> contentList) {
        this.contentList = contentList;
    }

    public void setClientInfoList(List<ClientInfo> clientInfoList) {
        this.clientInfoList = clientInfoList;
    }

    public List<CustomerAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<CustomerAddress> addressList) {
        this.addressList = addressList;
    }
}
