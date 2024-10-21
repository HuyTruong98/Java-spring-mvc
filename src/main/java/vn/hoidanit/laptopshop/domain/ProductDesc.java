package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_desc")
public class ProductDesc {
  @Id
  private long id;

  private String detailDesc;
  private String shortDesc;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Product product;

  public String getDetailDesc() {
    return detailDesc;
  }

  public void setDetailDesc(String detailDesc) {
    this.detailDesc = detailDesc;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

}
