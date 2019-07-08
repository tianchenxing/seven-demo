package com.tong.sevencommon.utils.customizedMapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 13:31 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
public interface InsertMapper<T>  extends Marker,
        tk.mybatis.mapper.common.base.insert.InsertMapper<T>,
        InsertSelectiveMapper<T>,
        MySqlMapper<T> {

}
