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
import java.util.ArrayList;
import java.util.Collections;
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
    public ResponseEntity<ByteArrayResource> createOrder(@RequestBody MultipleOrder multipleOrder) throws ExceedingLineLimitException, IOException {

//        List<Object> orderContentList = Arrays.asList(multipleOrder.getContentList().toArray());
//        List<Object> clientInfoList = Arrays.asList(multipleOrder.getClientInfoList().toArray());
//        List<Object> clientAddressList = Arrays.asList(multipleOrder.getAddressList().toArray());

        List<Object> orderContentList = Collections.singletonList(multipleOrder.getContentList());
        List<Object> clientInfoList = Collections.singletonList(multipleOrder.getClientInfoList());
        List<Object> clientAddressList = Collections.singletonList(multipleOrder.getAddressList());

        // заголовки ячеек
        List<String> sheetHeadlines = new ArrayList<>();
        sheetHeadlines.add("Order number");
        sheetHeadlines.add("Product name");
        sheetHeadlines.add("Product amount");
        sheetHeadlines.add("Price");

        MultipleOrderFileBuilder builder = new MultipleOrderFileBuilder();
        XSSFWorkbook workbook = builder
                .orderContentList(orderContentList)
                .clientInfoList(clientInfoList)
                .clientAddressList(clientAddressList)
                .buildWorkbook("Orders", sheetHeadlines);

//        MultipleOrderFileBuilder mofb = new MultipleOrderFileBuilder();
//
//        // make list with order info (content + client name needed)
//        XSSFWorkbook workbook = mofb.buildWorkbook("Orders", orderContentList);


        // записываем созданный в памяти Excel документ в файл
        try (FileOutputStream out = new FileOutputStream(new File("D:\\MultipleOrderFile.xls"))) {
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
