package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.CityDao;
import model.dao.ProvincialDao;
import model.dao.UserDao;
import vo.User;
import vo.city;
import vo.provincial;

public class test {
      public static void main(String[] args) throws SQLException {
    	 
    	  String a="a,b，c";
  		String s[]=a.split(",|，");
  		System.out.println(s[0]+s[2]);
	}
}
