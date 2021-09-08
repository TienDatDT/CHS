package DAL_CuaHangSach;

import javax.swing.JTextField;

public class CheckValid {
	public static boolean checkEmpty(JTextField field,StringBuilder sb,String st) {
	boolean flag=true;
	if(field.getText().equals("")) {
		sb.append(st).append("\n");
		flag=false;
	}
	else {
		flag=true;
	}
	return flag;
 }
	public static boolean checksl(JTextField field,StringBuilder sb) {
		boolean flag=true;
		if(!checkEmpty(field,sb,"Giá trị nhập không hợp lệ")) {
			return false;
		}
		try {
			int sl=Integer.parseInt(field.getText());
			if(sl<0) {
				sb.append("Giá trị nhập không hợp lệ");
				flag=false;
			}
		}catch(Exception e) {
			sb.append("Giá trị nhập không hợp lệ");
			flag=false;
		}
		return flag;
	}
	public static boolean checkgt(JTextField field,StringBuilder sb) {
		boolean flag=true;
		if(!checkEmpty(field,sb,"Giá trị nhập không hợp lệ")) {
			return false;
		}
		try {
			int gt=Integer.parseInt(field.getText());
			if(gt<10000) {
				sb.append("Giá trị nhập không hợp lệ");
				flag=false;
			}
		}catch(Exception e) {
			sb.append("Giá trị nhập không hợp lệ");
			flag=false;
		}
		return flag;
	}
}

