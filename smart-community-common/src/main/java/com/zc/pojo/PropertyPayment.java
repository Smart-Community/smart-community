package com.zc.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "property_payment")
@Accessors(chain = true)
public class PropertyPayment {
    @Id
    @Column(name = "property_payment_id")
    private long propertyPaymentId;
    @Column(name = "property_payment_house_id")
    private long propertyPaymentHouseId;
    @Column(name = "property_payment_payer_id")
    private long propertyPaymentPayerId;
    @Column(name = "property_payment_money")
    private BigDecimal propertyPaymentMoney;
    @Column(name = "property_payment_datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date propertyPaymentDatetime;


}
