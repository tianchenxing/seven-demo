package com.tong.sevencommon.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author by: Seven
 * @date: 2019/6/28
 * @Description: java相同字段
 */
public interface PojoConvertor {
    default <T> T convertTo(Class<T> clz) {
        return JSON.parseObject(JSON.toJSONBytes(this, SerializerFeature.DisableCircularReferenceDetect), clz);
    }
}
