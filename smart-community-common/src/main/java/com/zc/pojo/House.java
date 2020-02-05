package com.zc.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "house")
@Accessors(chain = true)
public class House {
    @Id
    @Column(name = "house_id")
    private long houseId;
    @Column(name = "house_tung_id")
    private int houseTungId;
    @Column(name = "house_unit_id")
    private int houseUnitId;
    @Column(name = "house_number")
    private int houseNumber;
    @Column(name = "house_area")
    private BigDecimal houseArea;
    @Column(name = "house_state")
    private short houseState;
    @Column(name = "house_money")
    private BigDecimal houseMoney;


}
