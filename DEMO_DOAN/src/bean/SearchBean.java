package bean;

import java.util.Date;

public class SearchBean {
	private String diemDi;
	private String diemDen;
	private Date thoiGian;
	public String getDiemDi() {
		return diemDi;
	}
	public void setDiemDi(String diemDi) {
		this.diemDi = diemDi;
	}
	public String getDiemDen() {
		return diemDen;
	}
	public void setDiemDen(String diemDen) {
		this.diemDen = diemDen;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	public SearchBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchBean(String diemDi, String diemDen, Date thoiGian) {
		super();
		this.diemDi = diemDi;
		this.diemDen = diemDen;
		this.thoiGian = thoiGian;
	}
	
}
