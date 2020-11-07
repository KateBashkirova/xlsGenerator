function createMultipleOrder() {
    var order = {
        "orderID":"1",
        "content":[
            {
                "productID":"30",
                "productName":"mechanical keyboard",
                "quantity":"1",
                "pricePerUnit":"15000"
            },
            {
                "productID":"31",
                "productName":"computer mouse",
                "quantity":"1",
                "pricePerUnit":"9000"
            }
        ],
        "clientInfo":[
            {
                "name":"John Smith",
                "email":"johnsmith@gmail.com",
                "phoneNumber":"89134569852"
            }
        ],
        "address":[
            {
                "country":"Russia",
                "city":"Moscow",
                "houseAddress":"Presnenskaya nab., 8 bldg. 1"
            }
        ],
    }
    var json = JSON.stringify(order);
    var request = new XMLHttpRequest();
    request.open("POST", "./createMultipleOrder", true);
    // request.responseType = "blob";
    request.setRequestHeader("Content-Type", "application/json");
    // request.onload = function () {
    //     var blob = request.response;
    //     var link = document.createElement('a');
    //     link.href = window.URL.createObjectURL(blob);
    //     link.download = "order.xls";
    //     link.click();
    // }
    request.send(json);
    alert(json);
    // return false;
}