
package Entity;

import java.util.Date;


public class NguoiMuon {
    public String maNM;
    public String tenNM;
    public Boolean gioiTinh = false;
    public Date ngayDK;
    public String Email;
    public Date ngaySinh;

    @Override
    public String toString() {
        return this.tenNM;
    }

    public String getMaNM() {
        return maNM;
    }

    public void setMaNM(String maNM) {
        this.maNM = maNM;
    }

    public String getTenNM() {
        return tenNM;
    }

    public void setTenNM(String tenNM) {
        this.tenNM = tenNM;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    
    
}
