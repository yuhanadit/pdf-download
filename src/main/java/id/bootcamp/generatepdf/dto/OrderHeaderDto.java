package id.bootcamp.generatepdf.dto;

import java.util.List;

public class OrderHeaderDto {
    private String reference;
    private Long amount;

    private List<OrderDetailDto> orderList;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<OrderDetailDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDetailDto> orderList) {
        this.orderList = orderList;
    }
}
