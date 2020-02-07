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
@Table(name = "permissions")
@Accessors(chain = true)
public class Permissions implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "permissions_role_id")
    private long permissionsRoleId;
    @Column(name = "permissions_power_url_id")
    private long permissionsPowerUrlId;


}
