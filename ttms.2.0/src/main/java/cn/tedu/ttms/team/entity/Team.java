package cn.tedu.ttms.team.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Team {
	private static final long serialVersionUID = -5365875262748186874L;
    /**表示项目id对应表中的物理主键*/
	private Integer id;
	/**项目名称(例如 纽约马拉松)*/
	private String name;
	
	/**项目编码(类似产品的序列号,具备业务特征)*/
	private Integer projectId;

	/**项目的有效性*/
	private Integer valid;
	/**项目备注*/
	private String note;
	/**项目的创建时间*/
	private Date createdTime;
	/**项目的修改时间*/
	private Date modifiedTime;
	/**项目的创建人*/
	private String createdUser;
	/**项目的修改*/
    private String modifiedUser;
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
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
    
    
    
    
    

}
