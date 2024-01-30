package id.bootcamp.generatepdf.dummies;

import id.bootcamp.generatepdf.dto.OrderDetailDto;
import id.bootcamp.generatepdf.dto.OrderHeaderDto;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    public static OrderHeaderDto getDummyData() {
        OrderHeaderDto orderHeaderDto = new OrderHeaderDto();
        orderHeaderDto.setReference("TRANS-XXVV-30012004-0001");
        orderHeaderDto.setAmount(140_000L);

        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setProductName("Product " + (i + 1));
            orderDetailDto.setProductDesc("Product kesehatan no-" + (i + 1));
            orderDetailDto.setProductPrice(10000L * (i + 1));
            orderDetailDto.setQuantity(i + 1L);
            orderDetailDto.setPrice((10000L * (i + 1)) * (i + 1L));
            orderDetailDtoList.add(orderDetailDto);
        }

        orderHeaderDto.setOrderList(orderDetailDtoList);

        return orderHeaderDto;
    }
}
