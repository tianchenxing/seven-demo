package com.tong.sevencore.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by: Seven
 * @date: 2019/4/15
 * @Description:煤质生产化验数据源表
 */
@Data
public class CoalQualityProduceAnalysisDataDomain implements Serializable{
    private Long id;
    /*采样时间*/
    private Date collectTime;
    /*采样设备*/
    private String collectThingCode;
    /*煤样名称*/
    private String coalSample;
    /*灰分ID*/
    private Long aadId;
    /*灰分*/
    private Double aadRate;
    private String aadStr;
    /*灰分修正值*/
    private Double aadCorrectionValue;
    /*全水分ID*/
    private Long mtId;
    /*全水分Mt*/
    private Double mt;
    private String mtStr;
    /*全水分Mt修正值*/
    private Double mtCorrectionValue;
    /*硫分ID*/
    private Long stId;
    /*硫分St.ad*/
    private Double stAd;
    private String stAdStr;
    /*硫分修正值*/
    private Double stCorrectionValue;
    /*是否已经提交,0-未提交，1-已提交*/
    private Integer state;
    /*创建时间*/
    private Date createTime;
    /*修改时间*/
    private Date updateTime;
    /*创建人*/
    private Long creator;
    /*更新人*/
    private Long updator;
    /*采样时间 yyyy-MM-dd HH:mm*/
    private String collectTimeStr;
    /*发热量，kcal/kg*/
    private Double qar;
    private String qarStr;
    /*发热量修正值，kcal/kg*/
    private Double qarCorrectionValue;
    /*生产系统，1，一期，2，二期*/
    private Integer termId;


}
