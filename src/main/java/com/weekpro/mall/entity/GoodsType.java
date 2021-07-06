package com.weekpro.mall.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * TODO
 * @ClassName GoodsType
 * @Author asuna
 * @Date 2021/6/30 - 10:57
 */


public class GoodsType {
	private int typeId;				//类别id
	private String typeName;		//类别名

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public GoodsType(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public GoodsType() {
	}
}
