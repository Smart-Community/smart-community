package com.zc.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 小帅气
 * @create 2020-02-29-19:11
 */

@Entity
@Table(name = "menu")
@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "menu_text")
    private String text;
    @Column(name = "menu_icon")
    private String icon;
    @Column(name = "menu_href")
    private String href;


}
