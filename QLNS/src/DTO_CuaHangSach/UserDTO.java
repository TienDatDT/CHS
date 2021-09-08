package DTO_CuaHangSach;

public class UserDTO {
	private int maNV;
	private String tenDangNhap;
	private String password;
	private String quyen;
	public UserDTO(int maNV, String tenDangNhap, String password, String quyen) {
		super();
		this.maNV = maNV;
		this.tenDangNhap = tenDangNhap;
		this.password = password;
		this.quyen = quyen;
	}
	public UserDTO(String tenDangNhap,String password) {
		this.tenDangNhap=tenDangNhap;
		this.password=password;
	}
	public UserDTO() {
		
	}

	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuyen() {
		return quyen;
	}
	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
	
}
