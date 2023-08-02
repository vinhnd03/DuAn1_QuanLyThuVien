
package Dao;

import Entity.Sach;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    public void insert(Sach model) {
        String sql = "Insert into SACH(Ma_SACH, TEN_SACH, TAC_GIA, NGAY_NHAP, GIA, SO_LUONG) VALUES (?,?,?,?,?,?)";
        XJdbc.update(sql,
                model.getMaSach(),
                model.getTenSach(),
                model.getTacGia(),
                model.getNgayNhap(),
                model.getGia(),
                model.getSoLuong()
        );
    }

    
    public void update(Sach model) {
        String sql = "UPDATE SACH SET TEN_SACH=?, TAC_GIA=?, NGAY_NHAP=?, GIA=?, SO_LUONG=? WHERE MA_SACH = ?";
        XJdbc.update(sql,
                model.getTenSach(),
                model.getTacGia(),
                model.getNgayNhap(),
                model.getGia(),
                model.getSoLuong(),
                model.getMaSach()
        );
    }

    
    public void delete(String MaSach) {
        String sql = "DELETE FROM SACH WHERE MA_SACH=?";
        XJdbc.update(sql, MaSach);
    }

    public List<Sach> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Sach WHERE TEN_SACH LIKE ?";
        return selectBySql(sql, "%" + keyword + "%");
    }
    
    public List<Sach> selectAll() {
        String sql = "SELECT * FROM SACH";
        return selectBySql(sql);
    }

    
    public Sach selectById(String MaSach) {
        String sql = "select * from SACH where Ma_SACH=?";
        List<Sach> list = selectBySql(sql, MaSach);
        return list.size() > 0  ? list.get(0) : null;
    }
    
    
    protected List<Sach> selectBySql(String sql, Object... args) {
        List<Sach> list = new ArrayList<Sach>();
        try{
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                Sach entity = new Sach();
                entity.setMaSach(rs.getString("Ma_SACH"));
                entity.setTenSach(rs.getString("TEN_SACH"));
                entity.setTacGia(rs.getString("TAC_GIA"));
                entity.setNgayNhap(rs.getDate("NGAY_NHAP"));
                entity.setGia(rs.getFloat("GIA"));
                entity.setSoLuong(rs.getInt("SO_LUONG"));            
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
