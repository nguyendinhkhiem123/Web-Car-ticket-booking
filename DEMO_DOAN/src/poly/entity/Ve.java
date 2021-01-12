package poly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Ve")
public class Ve {
	@Id
	@GeneratedValue
	@Column(name = "Mave")
	private int maVe;
	@ManyToOne
	@JoinColumn(name = "Iduser")
	private Members idUser;
	@ManyToOne
	@JoinColumn(name = "Matuyen")
	private Tuyens maTuyen;
	@Column(name = "Soluongve")
	private int soLuongVe;
	@Column(name = "Ngaydat")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayDat;
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public Ve() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaVe() {
		return maVe;
	}
	public void setMaVe(int maVe) {
		this.maVe = maVe;
	}
	
	public int getSoLuongVe() {
		return soLuongVe;
	}
	public void setSoLuongVe(int soLuongVe) {
		this.soLuongVe = soLuongVe;
	}
	public Members getIdUser() {
		return idUser;
	}
	public void setIdUser(Members idUser) {
		this.idUser = idUser;
	}
	public Tuyens getMaTuyen() {
		return maTuyen;
	}
	public void setMaTuyen(Tuyens maTuyen) {
		this.maTuyen = maTuyen;
	}
	public Ve(int maVe, Members idUser, Tuyens maTuyen, int soLuongVe) {
		super();
		this.maVe = maVe;
		this.idUser = idUser;
		this.maTuyen = maTuyen;
		this.soLuongVe = soLuongVe;
	}
	
	
	
}
