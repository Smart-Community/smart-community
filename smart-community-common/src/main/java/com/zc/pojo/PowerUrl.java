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
@Table(name = "power_url")
@Accessors(chain = true)
public class PowerUrl implements Serializable {
    @Id
    @Column(name = "power_url_id")
    private long powerUrlId;
    @Column(name = "power_url_prefix")
    private String powerUrlPrefix;


}
