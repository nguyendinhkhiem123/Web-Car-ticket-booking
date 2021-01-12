package poly.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Tuyen1")
public class Tuyens {
	@Id
	@GeneratedValue
	@Column(name = "Matuyen")
	private int maTuyen;
	@Column(name = "Tenxe")
	private String tenXe;
	@Column(name = "Diemdi")
	private String diemDi;
	@Column(name = "Diemden")
	private String diemDen;
	@Column(name = "Thoigian")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date thoiGian;
	@Column(name = "Giave")
	private int giaVe;
	@Column(name = "Sove")
	private int soVe;
	@OneToMany(mappedBy = "maTuyen" ,fetch =FetchType.EAGER)
	private Collection<Ve> ve;
	public Collection<Ve> getVe() {
		return ve;
	}
	public void setVe(Collection<Ve> ve) {
		this.ve = ve;
	}
	public int getMaTuyen() {
		return maTuyen;
	}
	public void setMaTuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}
	public String getTenXe() {
		return tenXe;
	}
	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}
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
	public int getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(int giaVe) {
		this.giaVe = giaVe;
	}
	public int getSoVe() {
		return soVe;
	}
	public void setSoVe(int soVe) {
		this.soVe = soVe;
	}
	public Tuyens() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tuyens(int maTuyen, String tenXe, String diemDi, String diemDen, Date thoiGian, int giaVe, int soVe) {
		super();
		this.maTuyen = maTuyen;
		this.tenXe = tenXe;
		this.diemDi = diemDi;
		this.diemDen = diemDen;
		this.thoiGian = thoiGian;
		this.giaVe = giaVe;
		this.soVe = soVe;
	}
	
	
	
}
