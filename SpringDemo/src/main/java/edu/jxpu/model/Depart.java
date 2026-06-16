package edu.jxpu.model;

/**
 * 学院实体类，和数据表mis_dept相对应 将数据库中的关系模型用对象模型的方式来表示
 */
public class Depart {
	/**
	 * 属性（这里和数据表mis_dept中的字段同名）
	 */
	private int oid;
	private String dno;
	private String dname;
	private String dinfo;
	/**
	 * 构造方法
	 */
	public Depart() {
	}
	public Depart(String dno, String dname, String dinfo) {
		this.dno = dno;
		this.dname = dname;
		this.dinfo = dinfo;
	}
	public Depart(int oid, String dno, String dname, String dinfo) {
		this.oid = oid;
		this.dno = dno;
		this.dname = dname;
		this.dinfo = dinfo;
	}
	/**
	 * 属性的setter/getter方法
	 */
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDinfo() {
		return dinfo;
	}
	public void setDinfo(String dinfo) {
		this.dinfo = dinfo;
	}
}