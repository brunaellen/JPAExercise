package com.alura.store.vo;

import java.time.LocalDate;

public class SalesReportVo {
  private String productName;
  private Long quantitySold;
  private LocalDate dateOfLastSale;
  
  public SalesReportVo(String productName, Long quantitySold, LocalDate dateOfLastSale) {
    this.productName = productName;
    this.quantitySold = quantitySold;
    this.dateOfLastSale = dateOfLastSale;
  }

  public String getProductName() {
    return productName;
  }

  public Long getQuantitySold() {
    return quantitySold;
  }

  public LocalDate getDate() {
    return dateOfLastSale;
  }

  @Override
  public String toString() {
    return "Sales Report [product Name=" + productName + ", quantity Sold=" + quantitySold + ", date Of Last Sale="
        + dateOfLastSale + "]";
  }
}
