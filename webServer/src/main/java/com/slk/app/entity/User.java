package com.slk.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERDB.user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="userId",nullable=false)
	private int userId;
	
	@Column(name="loginId",unique = true, nullable = false)
	private String loginId;
	@Column(name="first_name",nullable=false)
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="password",nullable=false)
	private String password;
	@Column(name="role")
	private String role;
	@Column(name="profile_pic")
	private String profilePic;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", loginId=" + loginId
				+ ", password=" + password + ", role=" + role + ", profilePic=" + profilePic + "]";
	}
 }
