package com.example.demo;


import java.sql.Timestamp;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class ServerInfo {

	@Id
	public Timestamp processDate;

	@Id
	public String serverName;

	public Integer apacheProcess;

	public Integer apacheConnection;

	public Integer ajpConnection;

	public Integer tomcatThread;

	public Integer tomcatSession;

	public Integer postgresConnection;

	public Integer apiConnection;


}

