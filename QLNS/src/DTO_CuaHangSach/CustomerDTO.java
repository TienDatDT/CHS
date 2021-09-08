package DTO_CuaHangSach;

public class CustomerDTO {
	private int IDKH;
	private String tenKH;
	private String diaChi;
	private int SDT;
	public CustomerDTO(int iDKH, String tenKH, String diaChi, int sDT) {
		super();
		IDKH = iDKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		SDT = sDT;
	}
	public int getIDKH() {
		return IDKH;
	}
	public void setIDKH(int iDKH) {
		IDKH = iDKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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
	public void setSDT(int sDT) {
		SDT = sDT;
	}
	
}
