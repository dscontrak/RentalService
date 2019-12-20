package com.hcl.traning.rentail.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
 
	@Column(name = "deleted", columnDefinition = "integer default 0", nullable = false)
    private int deleted;

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	
    
    
}
