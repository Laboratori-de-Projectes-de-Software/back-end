package com.alia.back_end_service.jpa.message;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "massage", unique = false, nullable = false)
    private String message;

    @Column(name = "length")
    private Integer length;

    @Column(name = "date")
    private String date;

    @ManyToOne

}
