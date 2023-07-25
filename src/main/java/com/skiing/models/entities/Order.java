package com.skiing.models.entities;

import com.skiing.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skiing_order")
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Setter(AccessLevel.NONE)
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn
    private User manager;

    @ManyToOne
    @JoinColumn
    private User courier;

    @Column(nullable = false)
    private Date startDate = Date.valueOf(LocalDate.now());

    @Column
    private Date endDateManager;

    @Column
    private Date endDateCourier;

    @Column
    private Date endDate;

    @Column
    private String deliveryAddress;

    @Column(nullable = false)
    private Boolean canceled = false;

    @ManyToMany
    @JoinColumn(nullable = false)
    private Set<Skiing> products = new HashSet<>();

    public Order(User client, String deliveryAddress, Set<Skiing> products) {
        this.client = client;
        this.deliveryAddress = deliveryAddress;
        this.products = products;
    }

    public String getStatus() {
        OrderStatus status = OrderStatus.NEW;
        if (canceled) {
            return OrderStatus.CANCELED.get();
        }
        if (endDate != null) {
            status = OrderStatus.DONE;
        } else if (manager != null && courier == null) {
            status = OrderStatus.ACCEPTED;
        } else if (courier != null && endDateCourier == null) {
            status = OrderStatus.DELIVERY;
        } else if (endDateCourier != null) {
            status = OrderStatus.DELIVERED;
        }
        return status.get();
    }

    public Integer getPrice() {
        return products.stream()
                .mapToInt(Skiing::getPrice)
                .sum();
    }
}
