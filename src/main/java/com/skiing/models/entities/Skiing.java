package com.skiing.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Skiing {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pictureUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer length;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer count;

    public Skiing(String name, String pictureUrl, String description, Integer length, Integer price, Integer count) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.length = length;
        this.price = price;
        this.count = count;
    }
}
