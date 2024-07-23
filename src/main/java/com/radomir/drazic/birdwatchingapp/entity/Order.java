package com.radomir.drazic.birdwatchingapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Order {

  private Long orderId;
  private String name;
  private String latinName;
  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Family> families;

}