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
	private int id;
	private String typeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public GoodsType() {
	}

	public GoodsType(int id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}
}
