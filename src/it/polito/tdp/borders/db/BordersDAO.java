package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {
	
//	public List<Country> loadAllCountries() {
//		
//		String sql = 
//				"SELECT ccode,StateAbb,StateNme " +
//				"FROM country " +
//				"ORDER BY ccode " ;
//
//		try {
//			Connection conn = DBConnect.getConnection() ;
//
//			PreparedStatement st = conn.prepareStatement(sql) ;
//			
//			ResultSet rs = st.executeQuery() ;
//			
//			List<Country> list = new LinkedList<Country>() ;
//			
//			while( rs.next() ) {
//				
//				Country c = new Country(
//						rs.getInt("ccode"),
//						rs.getString("StateAbb"), 
//						rs.getString("StateNme")) ;
//				
//				list.add(c) ;
//			}
//			
//			conn.close() ;
//			
//			return list ;
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null ;
//	}
	
public List<Country> loadAllCountries(int anno) {
		
		String sql = 
				"select distinct c1.CCode,c1.StateAbb,c1.StateNme from contiguity c2, country c1 where conttype='1' and c2.state2no=c1.CCode and year<=?" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
public List<Country> getConfini(int anno, Country c) {
		
		String sql = 
				"select c1.CCode,c1.StateAbb,c1.StateNme from contiguity c2, country c1 where conttype='1' and state1no=? and c2.state2no=c1.CCode and year<=?" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, c.getcCode());
			st.setInt(2, anno);
			
			ResultSet rs = st.executeQuery() ;
						
			while( rs.next() ) {
				
				Country cc = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				c.addConfine(cc);
				
			}
			
			conn.close() ;
			c.setConfinanti(c.getConfini().size());
			return c.getConfini() ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public static void main(String[] args) {
		List<Country> list ;
		BordersDAO dao = new BordersDAO() ;
		int anno=1831;
		list = dao.loadAllCountries(anno) ;
	for(Country c: list) {
			System.out.println(c);
		}
		for(Country c: list) {
			System.out.println(dao.getConfini(anno, c));
		}
	}
	
	
}
