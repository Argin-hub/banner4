package com.reactjs.banner4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RequestUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "user_agent")
  private String userAgent;

  @Column(name = "banner_id")
  private int bannerId;

  @Column(name = "date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate toDay = LocalDate.now();
}
