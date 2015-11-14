package net.th1232.model.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import net.th1232.model.AbstractEntity;

@SuppressWarnings("serial")
@Entity(name="role")
public class Role extends AbstractEntity{
	@Id
	@Column
	private String name;
	@Column
	private String description;
	@Transient
	private List<String> permissions;
	@Override
	public Serializable getPk() {
		return name;
	}
	public Role() {
	}
	public Role(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	
}
