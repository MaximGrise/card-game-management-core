package com.maxim.gamemanagement.core.persistence.domain.player;

import com.maxim.gamemanagement.core.persistence.domain.card.Card;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "player")
public class Player {

  @Id
  @NotNull
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(updatable = false, nullable = false, unique = true)
  private UUID id;

  @NotNull
  @Column(updatable = true, nullable = false, unique = true)
  private String name;

  public Player() {
  }

  public Player(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
