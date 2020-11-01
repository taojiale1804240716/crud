package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;
import vo.provincial;

public class ProvincialDao {
	public ArrayList<provincial> queryProvincial() throws SQLException{
		String sql="select * from provincial";
		JdbcUtil jdbc=new JdbcUtil();//数据库连接工具
		ResultSet rs=jdbc.query(sql);
		
		ArrayList<provincial> list=new ArrayList<provincial>();//城市列表
		provincial p=null;
		while(rs.next()){
			
			p=new provincial(rs.getInt("pid"),rs.getString("Provincial"));
			list.add(p);//添加进入
		}			
		return list;
	}      
	
}
