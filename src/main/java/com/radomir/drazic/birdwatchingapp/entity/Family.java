package com.radomir.drazic.birdwatchingapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Family {

  private Long familyId;
  private String name;
  private String latinName;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private Order order;
  @OneToMany(mappedBy = "genus", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Genus> geneses;

}