package com.example.demo.domain.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.Administrator;


@Dao
@ConfigAutowireable
public interface AdministratorDao {

    @Select
    Administrator getEntityById(String administratorId);


}

