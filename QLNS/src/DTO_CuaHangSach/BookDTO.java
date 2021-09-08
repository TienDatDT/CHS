package DTO_CuaHangSach;

public class BookDTO {
	private int IDSach;
	private String tenSach;
	private String tacGia;
	private String theLoai;
	private String NXB;
	private int giaTien;
	private int soLuong;
	public BookDTO(int iDSach, String tenSach, String tacGia, String theLoai, String nXB, int giaTien
			, int soLuong) {
		super();
		IDSach = iDSach;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.theLoai = theLoai;
		NXB = nXB;
		this.giaTien = giaTien;
		
		this.soLuong = soLuong;
	}
	
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getIDSach() {
		return IDSach;
	}
	public void setIDSach(int iDSach) {
		IDSach = iDSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public String getNXB() {
		return NXB;
	}
	public void setNXB(String nXB) {
		NXB = nXB;
	}
	public int getGiaNhap() {
		return giaTien;
	}
	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
}
