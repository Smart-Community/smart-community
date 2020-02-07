package com.zc.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "house_ownership")
@Accessors(chain = true)
public class HouseOwnership implements Serializable {


    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "house_ownership_house_id")
    private long houseOwnershipHouseId;
    @Column(name = "house_ownership_user_id")
    private long houseOwnershipUserId;
    @Column(name = "house_ownership_is_owner")
    private int houseOwnershipIsOwner;


}
