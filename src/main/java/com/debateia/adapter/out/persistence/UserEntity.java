package com.debateia.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  // @OneToMany(mappedBy = "user")
  // private List<Token> tokens;

  // @OneToOne
  // @JoinColumn(name = "match_id", nullable = true) // clave foranea a id de
  // match
  // private MatchEntity match;

  // mapeo al atributo "user" de la clase AIEntity
  // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =
  // FetchType.LAZY)
  // private List<AIEntity> aiList = new ArrayList<>();
}
