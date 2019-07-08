package com.tong.sevencommon.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 16:21 2019/6/28
 * @Modified_Author:
 * @Modified_Date:
 */
@Data
public class BasePojo implements PojoConvertor {
    /*主键自增设置*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creater;
    private Date createTime;
    private String updater;
    private Date updateTime;
}
