
package Dao;

import Entity.NhanVien;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public void insert(NhanVien model) {
        String sql = "Insert into NhanVien(Ma_NV, TEN_NV, EMAIL, VAI_TRO, MAT_KHAU) VALUES (?,?,?,?,?)";
        XJdbc.update(sql,
                model.getMaNV(),
                model.getTenNV(),
                model.getEmail(),
                model.getVaiTro(),
                model.getMatKhau()
        );
    }

    
    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET TEN_NV=?, EMAIL=?, VAI_TRO=?, MAT_KHAU=? WHERE MA_NV = ?";
        XJdbc.update(sql,
                model.getTenNV(),
                model.getEmail(),
                model.getVaiTro(),
                model.getMatKhau(),
                model.getMaNV()
        );
    }

    
    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE Ma_NV=?";
        XJdbc.update(sql, MaNV);
    }
    
    public List<NhanVien> CheckMaMV(){
        String sql = "SELECT Ma_NV FROM NhanVien";
        return selectBySql(sql);
    }

    
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySql(sql);
    }

    
    public NhanVien selectById(String MaNV) {
        String sql = "select * from NhanVien where Ma_NV=?";
        List<NhanVien> list = selectBySql(sql, MaNV);
        return list.size() > 0  ? list.get(0) : null;
    }

    
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try{
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("Ma_NV"));
                entity.setTenNV(rs.getString("TEN_NV"));
                entity.setEmail(rs.getString("Email"));
                entity.setVaiTro(rs.getBoolean("Vai_Tro"));
                entity.setMatKhau(rs.getString("Mat_Khau"));            
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
