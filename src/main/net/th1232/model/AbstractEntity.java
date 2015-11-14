package net.th1232.model;

import java.io.Serializable;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable, Comparable, Cloneable {
	@Transient
	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public abstract Serializable getPk();
	


	@Override
	public int compareTo(Object o) {
		if(o == null)return -9999;
		if(o.getClass().equals(this.getClass()))return -9999;
		if(this.getPk()==null)return -9999;
		if(this.getPk().getClass().isAssignableFrom(AbstractPk.class))
			return ((AbstractPk)this.getPk()).compareTo(((AbstractEntity)o).getPk());
		return 0;
	}

	public boolean equals(Object o){
		if(o == null)return false;
		if(o.getClass().equals(this.getClass()))return false;
		if(this.getPk()==null)return false;
		return this.getPk().equals(((AbstractEntity)o).getPk());
	}
	
	@PrePersist
	public void prePersist() {
		
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
}
