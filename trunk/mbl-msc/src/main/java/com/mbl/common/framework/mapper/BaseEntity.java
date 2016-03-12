package com.mbl.common.framework.mapper;

import java.sql.Timestamp;

public class BaseEntity implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 1L;

	/**  */
	private Timestamp creationDate;

	/**  */
	private String creationBy;

	/**  */
	private Timestamp lastUpdateDate;

	/**  */
	private String lastUpdateBy;

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

}
