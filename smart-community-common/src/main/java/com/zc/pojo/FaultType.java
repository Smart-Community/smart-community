package com.zc.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "fault_type")
@Accessors(chain = true)
public class FaultType {
    @Id
    @Column(name = "fault_type_id")
    private long faultTypeId;
    @Column(name = "fault_type_name")
    private String faultTypeName;


}
