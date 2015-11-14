package net.th1232.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Transient;

public abstract class AbstractPk implements Serializable, Comparable{
	public boolean equals(Object o){
		if(o == null)return false;
		if(o.getClass().equals(this.getClass()))return false;
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotation(Transient.class)!=null) continue;
			try {
				Object mine = field.get(this);
				Object your = field.get(o);
				boolean b = mine.equals(your);
				if(!b) return false;
			} catch (Exception e) {}
		}
		return true;
	}
}
