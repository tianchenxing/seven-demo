package com.tong.sevencommon.utils.customizedMapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.select.*;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 14:05 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
public interface SelectMapper <T> extends Marker,
        SelectOneMapper<T>,
        tk.mybatis.mapper.common.base.select.SelectMapper<T>,
        SelectAllMapper<T>,
        SelectCountMapper<T>,
        SelectByPrimaryKeyMapper<T>,
        ExistsWithPrimaryKeyMapper<T>,
        SelectByIdsMapper<T>,
        SelectByConditionMapper<T>,
        SelectCountByConditionMapper<T>,
        SelectByExampleMapper<T> {
}
