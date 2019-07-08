package com.tong.sevencommon.utils.customizedMapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.condition.DeleteByConditionMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 13:30 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
public interface DeleteMapper<T> extends Marker,
        tk.mybatis.mapper.common.base.delete.DeleteMapper<T>,
        DeleteByPrimaryKeyMapper<T>,
        DeleteByConditionMapper<T>,
        DeleteByIdsMapper<T> {
}
