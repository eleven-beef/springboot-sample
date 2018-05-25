package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dao.AdministratorDao;
import com.example.demo.domain.entity.Administrator;

@Service
public class AdministratorAuthService implements UserDetailsService{

	@Autowired
	AdministratorDao dao;



	/**
     * ユーザー読み込み
     */
    @Override
    public Administrator loadUserByUsername(String administratorId) throws UsernameNotFoundException {

        if(administratorId == null || "".equals(administratorId)) {
            throw new UsernameNotFoundException("IDが未入力です");
        }

        Administrator administrator = dao.getEntityById(administratorId);
        if(administrator == null) {
            throw new UsernameNotFoundException("IDが不正です。");
        }

        return administrator;
    }
}
