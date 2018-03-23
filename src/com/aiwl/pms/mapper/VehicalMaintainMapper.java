package com.aiwl.pms.mapper;

import com.aiwl.pms.entity.VehicalMaintain;
import com.aiwl.pms.entity.VehicalMaintainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehicalMaintainMapper {
    long countByExample(VehicalMaintainExample example);

    int deleteByExample(VehicalMaintainExample example);

    int deleteByPrimaryKey(Integer maintainId);

    int insert(VehicalMaintain record);

    int insertSelective(VehicalMaintain record);

    List<VehicalMaintain> selectByExample(VehicalMaintainExample example);

    VehicalMaintain selectByPrimaryKey(Integer maintainId);

    int updateByExampleSelective(@Param("record") VehicalMaintain record, @Param("example") VehicalMaintainExample example);

    int updateByExample(@Param("record") VehicalMaintain record, @Param("example") VehicalMaintainExample example);

    int updateByPrimaryKeySelective(VehicalMaintain record);

    int updateByPrimaryKey(VehicalMaintain record);
}