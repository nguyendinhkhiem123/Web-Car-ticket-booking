package poly.entity;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Member")
public class Members {
	@Id 
	@GeneratedValue
	@Column(name = "Id")
	private int iD;
	@Column(name = "Username")
	private String userName;
	@Column(name = "Password")
	private String passWord;
	@Column(name = "Role")
	private String role;
	@Column(name = "Hovaten")
	private String hovaten;
	@Column(name = "Sdt")
	private String sdt;
	@Column(name = "Address")
	private String address;
	@Column(name = "Email")
	private String email;
	@Column(name = "Image")
	private String image;
	@OneToMany(mappedBy = "idUser",fetch =FetchType.EAGER)
	private Collection<Ve> ve;
	public Collection<Ve> getVe() {
		return ve;
	}
	public void setVe(Collection<Ve> ve) {
		this.ve = ve;
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getHovaten() {
		return hovaten;
	}
	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Members(int iD, String userName, String passWord, String role, String hovaten, String sdt, String address,
			String email) {
	
		this.iD = iD;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
		this.hovaten = hovaten;
		this.sdt = sdt;
		this.address = address;
		this.email = email;
	}
	public Members(int iD, String userName, String passWord, String role, String hovaten, String sdt, String address,
			String email, String imgae) {
		
		this.iD = iD;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
		this.hovaten = hovaten;
		this.sdt = sdt;
		this.address = address;
		this.email = email;
		this.image = imgae;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Members() {
		super();
	}
	
	

	
	
}
