
package Dao;

import Entity.NguoiMuon;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiMuonDAO {
    public void insert(NguoiMuon model) {
        String sql = "Insert into NGUOIMUON(Ma_NM, TEN_NM, EMAIL, NGAY_SINH, GIOI_TINH, NGAY_DK) VALUES (?,?,?,?,?,?)";
        XJdbc.update(sql,
                model.getMaNM(),
                model.getTenNM(),
                model.getEmail(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getNgayDK()
        );
    }

    
    public void update(NguoiMuon model) {
        String sql = "UPDATE NguoiMuon SET TEN_NM=?, Email=?, NGAY_SINH=?, GIOI_TINH=?, NGAY_DK=? WHERE MA_NM = ?";
        XJdbc.update(sql,
                model.getTenNM(),
                model.getEmail(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getNgayDK(),
                model.getMaNM()
        );
    }

    
    public void delete(String MaNM) {
        String sql = "DELETE FROM NguoiMuon WHERE Ma_NM=?";
        XJdbc.update(sql, MaNM);
    }

    public List<NguoiMuon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiMuon WHERE Ten_NM LIKE ?";
        return selectBySql(sql, "%" + keyword + "%");
    }
    
    public List<NguoiMuon> selectAll() {
        String sql = "SELECT * FROM NguoiMuon";
        return selectBySql(sql);
    }

    
    public NguoiMuon selectById(String MaNM) {
        String sql = "select * from NguoiMuon where Ma_NM=?";
        List<NguoiMuon> list = selectBySql(sql, MaNM);
        return list.size() > 0  ? list.get(0) : null;
    }

    
    protected List<NguoiMuon> selectBySql(String sql, Object... args) {
        List<NguoiMuon> list = new ArrayList<NguoiMuon>();
        try{
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                NguoiMuon entity = new NguoiMuon();
                entity.setMaNM(rs.getString("Ma_NM"));
                entity.setTenNM(rs.getString("TEN_NM"));
                entity.setEmail(rs.getString("Email"));
                entity.setNgaySinh(rs.getDate("NGAY_SINH"));
                entity.setGioiTinh(rs.getBoolean("GIOI_TINH"));
                entity.setNgayDK(rs.getDate("NGAY_DK"));            
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
