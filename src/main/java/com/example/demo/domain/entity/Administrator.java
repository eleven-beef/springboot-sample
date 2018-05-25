package com.example.demo.domain.entity;

import java.sql.Timestamp;
import java.util.Collection;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Administrator implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** 管理者SEQ（必須） */
    @Id
    public Integer administratorSeq;

    /** 管理者状態（必須） */
    public String administratorStatus;

    /** 管理者ID（必須） */
    public String administratorId;

    /** 管理者パスワード（必須） */
    public String administratorPassword;

    /** 管理者メールアドレス */
    public String mail;

    /** 管理者氏名(性)（必須） */
    public String administratorLastName;

    /** 管理者氏名(名） */
    public String administratorFirstName;

    /** 管理者フリガナ(性)（必須） */
    public String administratorLastKana;

    /** 管理者フリガナ(名) */
    public String administratorFirstKana;

    /** 利用開始日 */
    public Timestamp useStartDate;

    /** 利用終了日 */
    public Timestamp useEndDate;


    /** 登録日時（必須） */
    public Timestamp registTime;

    /** 更新日時（必須） */
    public Timestamp updateTime;

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getPassword() {
		return this.administratorPassword;
	}

	@Override
	public String getUsername() {
		return this.administratorLastName + this.administratorFirstName;
	}

}


