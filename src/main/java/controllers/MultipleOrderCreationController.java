package controllers;

import POJO.MultipleOrder;
import customExeptions.ExceedingLineLimitException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import xlsBuilders.MultipleOrderFileBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;


@Controller
public class MultipleOrderCreationController {

    @GetMapping(value = "/createMultipleOrder")
    public ModelAndView showNewOrderForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createMultipleOrder");
        return modelAndView;
    }

    @PostMapping(value = "/createMultipleOrder", consumes = "application/json")
    public ResponseEntity<ByteArrayResource> createOrder(@RequestBody MultipleOrder multipleOrder) throws ExceedingLineLimitException, IOException, InvocationTargetException {
//        System.out.println(orderContent.get(0).getProductName());

        // FIXME: list vs array vs array list?
        Object[] orderContentArray = multipleOrder.getContentList().toArray();
        Object[] clientInfoArray = multipleOrder.getClientInfoList().toArray();
        Object[] clientAddressArray = multipleOrder.getAddressList().toArray();

        List<Object> orderContentList = Arrays.asList(multipleOrder.getContentList().toArray());
        List<Object> clientInfoList = Arrays.asList(multipleOrder.getClientInfoList().toArray());
        List<Object> clientAddressList = Arrays.asList(multipleOrder.getAddressList().toArray());

        MultipleOrderFileBuilder mofb = new MultipleOrderFileBuilder();
        // set values
        mofb.orderContent(orderContentList)
                .clientInfo(clientInfoList)
                .clientAddress(clientAddressList);
        // make list with order info (content + client name needed)
        XSSFWorkbook workbook = mofb.buildWorkbook("Orders", orderContentList);


        // записываем созданный в памяти Excel документ в файл
        try (FileOutputStream out = new FileOutputStream(new File("D:\\File.xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        ByteArrayResource bt = new ByteArrayResource(bos.toByteArray());
        HttpHeaders headers = getHttpHeaders();
        return new ResponseEntity<>(bt, headers, HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
        headers.add("Content-Disposition", "attachment;filename=" + "order.xls");
        return headers;
    }
}
