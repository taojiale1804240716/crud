package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;
import vo.city;

public class CityDao {

	public ArrayList<city> queryCity() throws SQLException{
		String sql="select * from City";
		JdbcUtil jdbc=new JdbcUtil();//数据库连接工具
		ResultSet rs=jdbc.query(sql);
		
		ArrayList<city> list=new ArrayList<city>();//城市列表
		city c=null;
		while(rs.next()){
			c=new city(rs.getInt("cid"),rs.getString("city"),rs.getInt("pid"));
			list.add(c);//添加进入
		}			
		
		return list;
	}
	public ArrayList<city> queryprovinceCity(String provinceCode) throws SQLException{
		String sql="select * from City where pid="+provinceCode;
		JdbcUtil jdbc=new JdbcUtil();//数据库连接工具
		ResultSet rs=jdbc.query(sql);
		
		ArrayList<city> list=new ArrayList<city>();//城市列表
		city c=null;
		while(rs.next()){
			c=new city(rs.getInt("cid"),rs.getString("city"),rs.getInt("pid"));
			list.add(c);//添加进入
		}			
		
		return list;
	}
	
}
