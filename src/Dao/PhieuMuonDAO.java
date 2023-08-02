
package Dao;

import Entity.PhieuMuon;
import Entity.Sach;
import Helper.XDate;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhieuMuonDAO {
    public void insert(PhieuMuon model) {
        String sql = "Insert into PhieuMuon(Ma_PM, MA_NM, MA_SACH, MA_NV, NGAY_MUON, NGAY_TRA) VALUES (?,?,?,?,?,?)";
        XJdbc.update(sql,
                model.getMaPM(),
                model.getMaNM(),
                model.getMaSach(),
                model.getMaNV(),
                model.getNgayMuon(),
                model.getNgayTra()
        );
    }

    
    public void update(PhieuMuon model) {
        String sql = "UPDATE PhieuMuon SET MA_NM=?, MA_SACH=?, MA_NV=?, NGAY_MUON=?, NGAY_TRA=? WHERE MA_PM = ?";
        XJdbc.update(sql,
                model.getMaNM(),
                model.getMaSach(),
                model.getMaNV(),
                model.getNgayMuon(),
                model.getNgayTra(),
                model.getMaPM()
        );
    }

    
    public void delete(String MaPM) {
        String sql = "DELETE FROM PhieuMuon WHERE Ma_PM=?";
        XJdbc.update(sql, MaPM);
    }

    
    public List<PhieuMuon> selectAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return selectBySql(sql);
    }

    public List<Integer> selectYears(){
        String sql = "SELECT DISTINCT year(Ngay_Muon) FROM PhieuMuon ORDER BY Year(NGAY_MUON) DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public PhieuMuon selectById(String MaPM) {
        String sql = "select * from PhieuMuon where Ma_PM=?";
        List<PhieuMuon> list = selectBySql(sql, MaPM);
        return list.size() > 0  ? list.get(0) : null;
    }

    
    protected List<PhieuMuon> selectBySql(String sql, Object... args) {
        List<PhieuMuon> list = new ArrayList<PhieuMuon>();
        try{
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                PhieuMuon entity = new PhieuMuon();
                entity.setMaPM(rs.getString("Ma_PM"));
                entity.setMaSach(rs.getString("Ma_Sach"));
                entity.setMaNM(rs.getString("Ma_NM"));
                entity.setMaNV(rs.getString("Ma_NV"));
                entity.setNgayMuon(rs.getDate("NGAY_MUON"));
                entity.setNgayTra(rs.getDate("NGAY_TRA"));                          
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
   
}
