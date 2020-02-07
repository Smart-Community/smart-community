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
@Table(name = "role")
@Accessors(chain = true)
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "role_name")
    private String roleName;


}
