package model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;
import vo.Page;
import vo.User;

public class UserDao {
       
	
	public  User getUser(String userName) throws SQLException, FileNotFoundException, IOException{
		 User user = null;
		
		JdbcUtil jdbcutil=new JdbcUtil();
	
		String sql="select * from t_user where userName='"+userName+"'";
		

	    ResultSet rs=jdbcutil.query(sql);
		
		if(rs.next()){			
			  user =new User(rs.getString("userName"),rs.getString("password"),rs.getString("charName"),rs.getString("role"));
		}
	    
		jdbcutil.close();
		return user;		
	}
	public List<User> getUserList() throws SQLException, FileNotFoundException, IOException{
   	  //准备一个搜索所有user的sql语句
		String sql="select * from t_user";
		JdbcUtil jdbcutil=new JdbcUtil();
		ResultSet rs=jdbcutil.query(sql);
		//实例化user的列表
		ArrayList<User> list=new ArrayList<User>();
		User user=null;
		while(rs.next()){
			user=new User(rs.getString("userName"),rs.getString("password"),rs.getString("charName"),rs.getString("role"));
			list.add(user);
		}
		return list;
   }
	public void add(User u) throws SQLException{
		String sql = "insert into t_user(role,userName,password,charName,provincial,city,email)values(?,?,?,?,?,?,?)";
		JdbcUtil jdbc=new JdbcUtil();
		PreparedStatement  ps=jdbc.getconnection().prepareStatement(sql);
		//得到数据库的连接
        ps.setString(1,u.getRole());
        ps.setString(2,u.getUserName());
        ps.setString(3,u.getPassword());
        ps.setString(4,u.getChrName());
        ps.setString(5,u.getprovincial());
        ps.setString(6,u.getcity());
        ps.setString(7, u.getemial());
        
        
        int row = ps.executeUpdate();//
        if(row>0){
            System.out.println("成功添加了" + row + "条数据！");
        }
	}
	public ArrayList<User> query(User user, Page page) {
		ArrayList<User> list = new ArrayList<User>(); // 存放查询结果的集合
		StringBuffer condition = new StringBuffer();// 查询条件
		if (user.getUserName() != null && !"".equals(user.getUserName())) { // 判断是否有该查询条件
			condition.append(" and userName like '%")
					.append(user.getUserName()).append("%'");
		}
		if (user.getChrName() != null && !"".equals(user.getChrName())) { //
			condition.append(" and charName like '%").append(user.getChrName())
					.append("%'");
		}
		if (user.getemial() != null && !"".equals(user.getemial())) { //
			condition.append(" and email like '%").append(user.getemial())
					.append("%'");
		}
		if (user.getprovincial() != null
				&& !"".equals(user.getprovincial())) { //
			condition.append(" and provincial like '%")
					.append(user.getprovincial()).append("%'");
		}	
		int begin = page.getPageSize() * (page.getPageNumber() - 1);
		String sql = "select * ";
		sql = sql + " from t_user" ;
		sql = sql + " where  1=1 ";
		sql = sql + condition + " order by " + page.getSort() + " "
				+ page.getSortOrder() + " limit " + begin + ","
				+ page.getPageSize();

		System.out.println(sql);
		// DatabaseConnection类封装了数据库驱动加载和连接
		 JdbcUtil jdbc=new JdbcUtil();
		 
		
		try {
			
			ResultSet rs = jdbc.query(sql);
			while (rs.next()) {
				User u= new User();
				u.setChrName(rs.getString("charName"));
				u.setcity(rs.getString("city"));
				u.setemial(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setprovincial(rs.getString("provincial"));
				u.setUserName(rs.getString("userName"));
				u.setRole(rs.getString("role"));
				//实例化user
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close();// 6.关闭连接
		}

		return list;
	}
	public int count(User user, Page page){
		int count=0;//存放总数据条数
		 // 存放查询结果的集合
		StringBuffer condition = new StringBuffer();// 查询条件
		if (user.getUserName() != null && !"".equals(user.getUserName())) { // 判断是否有该查询条件
			condition.append(" and userName like '%")
					.append(user.getUserName()).append("%'");
		}
		if (user.getChrName() != null && !"".equals(user.getChrName())) { //
			condition.append(" and charName like '%").append(user.getChrName())
					.append("%'");
		}
		if (user.getemial() != null && !"".equals(user.getemial())) { //
			condition.append(" and email like '%").append(user.getemial())
					.append("%'");
		}
		if (user.getprovincial() != null
				&& !"".equals(user.getprovincial())) { //
			condition.append(" and provincial like '%")
					.append(user.getprovincial()).append("%'");
		}	
		int begin = page.getPageSize() * (page.getPageNumber() - 1);
		String sql = "select * ";
		sql = sql + " from t_user" ;
		sql = sql + " where  1=1 ";
		sql = sql + condition + " order by " + page.getSort() + " "
				+ page.getSortOrder() ;
		System.out.println(sql);
		// DatabaseConnection类封装了数据库驱动加载和连接
		 JdbcUtil jdbc=new JdbcUtil();	 		
		try {
			
			ResultSet rs = jdbc.query(sql);
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close();// 6.关闭连接
		}
		return count;
	}
	public boolean delete(String userName) throws SQLException{
		JdbcUtil jdbc=new JdbcUtil();
		String sql="delete from t_user where userName=?";
		PreparedStatement  ps=jdbc.getconnection().prepareStatement(sql);//得到数据库连接
		ps.setString(1, userName);
		ps.executeUpdate();//删除
		System.out.println("数据库删除成功");
		return true;
	}
	public boolean updateuser(User user) throws SQLException{
		//首先删除delete 	
		delete(user.getUserName());
		add(user);
		return true;
	}

	
}
