package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
    @PropertySource("classpath:email.properties")
})
@Configuration
public class ConfigurationProperty {

	@Value("${com.mail.from}")
	private String from;

	@Value("${com.mail.password}")
	private String password;

	@Value("${com.mail.host}")
	private String host;

	@Value("${com.mail.username}")
	private String username;

	@Value("${com.mail.port}")
	private int port;
	@Value("${com.mail.smtp.auth}")
	private boolean smtpAuth;
	@Value("${com.mail.smtp.starttls.enable}")
	private boolean smtpStarttlsenable;

	@Value("${com.mail.smtp.ssl.required}")
	private boolean smtpSSLRequiredAuth;

	@Value("${com.mail.smtp.ssl.protocols}")
	private String smtpSSLprotocols;

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public boolean isSmtpStarttlsenable() {
		return smtpStarttlsenable;
	}
	public void setSmtpStarttlsenable(boolean smtpStarttlsenable) {
		this.smtpStarttlsenable = smtpStarttlsenable;
	}

	public boolean isSmtpSSLRequiredAuth() {
		return smtpSSLRequiredAuth;
	}
	public void setSmtpSSLRequiredAuth(boolean smtpSSLRequiredAuth) {
		this.smtpSSLRequiredAuth = smtpSSLRequiredAuth;
	}
	public String getSmtpSSLprotocols() {
		return smtpSSLprotocols;
	}
	public void setSmtpSSLprotocols(String smtpSSLprotocols) {
		this.smtpSSLprotocols = smtpSSLprotocols;
	}

}
