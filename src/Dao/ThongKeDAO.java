/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args ){
        try {
            List<Object[]>list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0;i<cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Object[]> getLuongNguoiMuon() {
        String sql = "{CALL sp_ThongKeNguoiMuon}";
        String[] cols = {"Nam","SoLuong","DauTien","CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getPhieuMuon(Integer nam) {
        String sql = "{CALL sp_ThongKePhieuMuon (?)}";
        String[] cols = {"Thang", "SoLuong",};
        return this.getListOfArray(sql, cols, nam);
    }
    
    public List<Object[]> getSachDaTra() {
        String sql = "{CALL sp_ThongKeHanTra}";
        String[] cols = {"Ma_PM","Ten_Sach","Ten_NM","Ngay_Tra"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getSachChuaTra() {
        String sql = "{CALL sp_ThongKeHanTra2}";
        String[] cols = {"Ma_PM","Ten_Sach","Ten_NM","Ngay_Tra"};
        return this.getListOfArray(sql, cols);
    }
}
