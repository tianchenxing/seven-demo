package com.tong.sevencore.dto;





import com.tong.sevencore.pojo.CoalQualityProduceAnalysisDataDomain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by: Seven
 * @date: 2019/4/15
 * @Description:
 */
public class CoalQualityWebDTO implements Serializable {
    /*详情信息id*/
    private Long infoId;
    /*页码*/
    private Integer pageNo;
    /*查询信息页码数*/
    private Integer pageNos;
    /*总条数*/
    private Long totalNo;
    /*起始信息编号*/
    private Integer startNo;
    /*截止信息编号*/
    private Integer endNo;
    /*前端页面展示*/
    private  Map<String, Map<String,List>> dispalyMap  ;
    /*报表展示，key：项目，value:list*/
    private Map<String,List> reportMap ;

    private CoalQualityProduceAnalysisDataDomain coalQualityProduceAnalysisDataDomain;
    public CoalQualityWebDTO() {
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageNos() {
        return pageNos;
    }

    public void setPageNos(Integer pageNos) {
        this.pageNos = pageNos;
    }

    public Long getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(Long totalNo) {
        this.totalNo = totalNo;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public Integer getEndNo() {
        return endNo;
    }

    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }

    public Map<String, Map<String, List>> getDispalyMap() {
        return null == dispalyMap ? new HashMap<String,Map<String,List>>():dispalyMap;
    }

    public void setDispalyMap(Map<String, Map<String, List>> dispalyMap) {
        this.dispalyMap = dispalyMap;
    }

    public Map<String, List> getReportMap() {
        return reportMap;
    }

    public void setReportMap(Map<String, List> reportMap) {
        this.reportMap = reportMap;
    }

    public CoalQualityProduceAnalysisDataDomain getCoalQualityProduceAnalysisDataDomain() {
        return null == coalQualityProduceAnalysisDataDomain? new CoalQualityProduceAnalysisDataDomain():coalQualityProduceAnalysisDataDomain;
    }

    public void setCoalQualityProduceAnalysisDataDomain(CoalQualityProduceAnalysisDataDomain coalQualityProduceAnalysisDataDomain) {
        this.coalQualityProduceAnalysisDataDomain = coalQualityProduceAnalysisDataDomain;
    }
}
