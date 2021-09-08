package DTO_CuaHangSach;

public class NhanVienDTO {
	private int ID;
	private String tenNV;
	private String ngaySinh;
	private String gioiTinh;
	private String diaChi;
	private int SDT;
	public NhanVienDTO(int iD, String tenNV, String ngaySinh, String gioiTinh, String diaChi, int SDT) {
		super();
		ID = iD;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.SDT = SDT;
	}
	public NhanVienDTO() {
		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getSDT() {
		return SDT;
	}
	public void setSDT(int SDT) {
		this.SDT=SDT;
	}
	
	
}
