package com.example.demo;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;


@Dao
@ConfigAutowireable
public interface ServerInfoDao {

    @Select
    List<ServerInfo> selectAll();

    @Select
    List<ServerInfo> selectByServerName(String serverName);


}
