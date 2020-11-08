package POJO;


import POJO.MultipleOrderAdditional.ClientInfo;
import POJO.MultipleOrderAdditional.CustomerAddress;
import POJO.MultipleOrderAdditional.OrderContent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MultipleOrder {
    public Integer orderID;
    @JsonProperty("content") // без него до массива массивов не дотягивается
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

    public List<OrderContent> getContentList() { return contentList; }

    public void setContentList(List<OrderContent> contentList) {
        this.contentList = contentList;
    }

    public List<ClientInfo> getClientInfoList() {
        return clientInfoList;
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
