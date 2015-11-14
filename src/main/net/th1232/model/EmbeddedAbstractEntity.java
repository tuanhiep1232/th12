package net.th1232.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class EmbeddedAbstractEntity extends AbstractEntity{
	@Column(name="created_by")
	protected  String createdBy;//username
	@Column(name="created_time")
	protected  Date createdTime;
	@Column(name="last_updated_by")
	protected  String lastUpdatedBy;//username
	@Column(name="last_updated_time")
	protected  Date lastUpdatedTime;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		
	}
	@PreUpdate
	public void preUpdate() {
		
	}
	@PostLoad
	public void postLoad() {
		
	}
	@PostPersist
	public void postPersist() {
		
	}
	@PostRemove
	public void postRemove() {
		
	}
	@PostUpdate
	public void postUpdate() {
		
	}
	/**
	 * getters and setters
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}	
}
