package com.aiwl.pms.mapper;

import com.aiwl.pms.entity.GoodsClass;
import com.aiwl.pms.entity.GoodsClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsClassMapper {
    long countByExample(GoodsClassExample example);

    int deleteByExample(GoodsClassExample example);

    int deleteByPrimaryKey(Integer classid);

    int insert(GoodsClass record);

    int insertSelective(GoodsClass record);

    List<GoodsClass> selectByExample(GoodsClassExample example);

    GoodsClass selectByPrimaryKey(Integer classid);

    int updateByExampleSelective(@Param("record") GoodsClass record, @Param("example") GoodsClassExample example);

    int updateByExample(@Param("record") GoodsClass record, @Param("example") GoodsClassExample example);

    int updateByPrimaryKeySelective(GoodsClass record);

    int updateByPrimaryKey(GoodsClass record);
}