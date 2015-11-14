package net.th1232.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import net.th1232.model.AbstractEntity;

@SuppressWarnings("serial")
@Entity(name="Users")
public class User extends AbstractEntity{
	@Column(name="username")
	@Id
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="language_code")
	private LanguageCode languageCode=LanguageCode.en;
	@Column(name="name")
	private String name;
	@Column(name="last_login_dated")
	private Date lastLoginTime;
	@Column(name="last_login_ip")
	private Date lastLoginIp;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="user_role",
			joinColumns={@JoinColumn(name="username",referencedColumnName="username")},
			inverseJoinColumns={@JoinColumn(name="name", referencedColumnName="role")}
			)
	private List<Role> roles = new ArrayList<Role>();
	@Override
	public Serializable getPk() {
		return username;
	}
	public User() {
	}
	
	
	public User(String username) {
		super();
		this.username = username;
	}
	/**
	 * getters and setters
	 */
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LanguageCode getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(LanguageCode languageCode) {
		this.languageCode = languageCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(Date lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public List<Role> getRoles() {
		return roles;
	}	
}
