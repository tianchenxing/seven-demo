package com.tong.sevencommon.mapper;

import com.tong.sevencommon.pojo.TbBaseUserInfo;
import com.tong.sevencommon.utils.customizedMapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends CrudMapper<TbBaseUserInfo> {
}