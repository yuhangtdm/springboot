package com.dity.tag.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sy_dict_item")
public class SyDictItem implements Serializable{
	
	/**
	 * 字典表
	 */
	private static final long serialVersionUID = 5835797947579493448L;

	@Id
	private Integer id;
	/**
	 * 字典名称
	 */
	@Column(name="dict_name")
	private String name;
	/**
	 * 字典KEY
	 */
	@Column(name="dict_key")
	private String key;
	/**
	 * 对照码
	 */
	@Column(name="dict_code")
	private String code;
	
	/**
	 * 对照值
	 */
	@Column(name="dict_desc")
	private String desc;
	
	/**
	 * 是否启用
	 */
	@Column(name="dict_status")
	private String status;
	/**
	 * 所属分组
	 */
	@Column(name="dict_group")
	private String group;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 备注
	 */
	private String remark;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
