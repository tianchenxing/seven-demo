package com.tong.sevencommon.utils.customizedMapper;

/**
 * @Author: SevenTian
 * @Description: 自定义的增删该查接口，特别注意，该接口不能被扫描到，否则会出错
 * @Create_Date: 13:23 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
public interface CrudMapper<T> extends
        InsertMapper<T>,
        DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T>{
}
