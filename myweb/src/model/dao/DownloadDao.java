package model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;
import vo.User;
import vo.Download;
public class DownloadDao {
       
	public   ArrayList<Download> getDownloadList() throws SQLException, FileNotFoundException, IOException{
		 Download download = null;
		 ArrayList<Download> downloadlist=new ArrayList<Download>();
		
		JdbcUtil jdbcutil=new JdbcUtil();
		
		String sql="select * from t_downloadlist";
		
		
		//PreparedStatement pst=jdbcutil.getPreparedStatement();	
		
		ResultSet rs=jdbcutil.query(sql);
		
		while(rs.next()){
			   download=new Download(rs.getInt("id"),rs.getString("name"),rs.getString("path"),rs.getString("description"),rs.getLong("size"),rs.getInt("star"),rs.getString("image"));
			   downloadlist.add(download);
		}
	    System.out.println(downloadlist.size());
		jdbcutil.close();
		return downloadlist;		
	}
}
