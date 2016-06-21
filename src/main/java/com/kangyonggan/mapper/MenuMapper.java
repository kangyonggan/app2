package com.kangyonggan.mapper;

import com.kangyonggan.model.Menu;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends MyMapper<Menu> {

    List<Menu> selectMenusByCodeAndUserId(@Param("code") String code, @Param("userId") Long userId);
}