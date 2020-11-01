package tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JdbcUtil {
	private static final String user = "taojiale";
	private static final String password = "102311tao";
	public static final String url = "jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=UTF-8&useSSL=false"; // ���ݿ�����
	public static final String name = "com.mysql.jdbc.Driver";

	public Connection connection = null;
	public PreparedStatement preparedStatement = null;

	public JdbcUtil() {
		try {
			Class.forName(name);
			connection = (Connection) DriverManager.getConnection(url, user, password);// ��ȡ����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.connection.close();
			this.preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql) {
		ResultSet resultSet = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql); // ׼��ִ�����
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {
			System.out.println("执行语句失败");
			e.printStackTrace();
		}
		return resultSet;
	}

	public boolean executeNonquery(String sql) {
		boolean flag = false;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (Exception e) {
			System.out.println("�������ݿ�ʱ���ִ��󣡣�");
			e.printStackTrace();
		}
		return flag;
	}

	public Connection getconnection() throws SQLException {
		return connection;
	}
	

}
