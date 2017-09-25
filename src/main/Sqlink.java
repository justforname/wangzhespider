package main;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Sqlink {
	private String driver = "com.mysql.jdbc.Driver";
	private String host;
	private String name;
	private String pword;
	private java.sql.Connection conn;
	private java.sql.Statement stmt;
	
	public Sqlink(){
		host = Config.host;
		name = Config.name;
		pword = Config.pword;
	}
	
	public void connetMsql(){
		try {
			Class.forName(driver);
			System.out.println("加载数据库驱动成功!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("加载数据库驱动失败!");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(host, name, pword);
			stmt = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//写入用户数据
	public int insert(UserInfo userinfo) {
	    int i = 0;
	    String sql = "insert into userinfo (open_id,zone_area_id,nick_name,game_name,service_name,ladder_score,rank_desc,rank_star,winning_percentage,win_desc,head_img_url,game_svr_entity,game_seq,relay_svr_entity) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    //System.out.println("--nick--"+userinfo.nick_name.replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userinfo.open_id);
	        pstmt.setString(2, userinfo.zone_area_id);
	        pstmt.setString(3, userinfo.nick_name.replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
	        pstmt.setString(4, userinfo.game_name);
	        pstmt.setString(5, userinfo.service_name);
	        pstmt.setString(6, userinfo.ladder_score);
	        pstmt.setString(7, userinfo.rank_desc);
	        pstmt.setString(8, userinfo.rank_star);
	        pstmt.setString(9, userinfo.winning_percentage);
	        pstmt.setString(10, userinfo.win_desc);
	        pstmt.setString(11, "'"+userinfo.head_img_url+"'");
	        pstmt.setString(12, userinfo.battle_listmap.get(0).get("game_svr_entity"));
	        pstmt.setString(13, userinfo.battle_listmap.get(0).get("game_seq"));
	        pstmt.setString(14, userinfo.battle_listmap.get(0).get("relay_svr_entity"));
	        
	        i = pstmt.executeUpdate();
	        //pstmt.close();
	        //conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	//获取需要使用的 战绩信息
	public List<HashMap<String, String>> getUser(){
		List<HashMap<String, String>> listmap = new LinkedList<HashMap<String,String>>();
		try {
			ResultSet rs = this.getData("select open_id,game_svr_entity,game_seq,relay_svr_entity from userinfo where is_grab is null");
			while (rs.next()){
				HashMap<String, String> smap = new HashMap<String,String>();
				smap.put("open_id", rs.getString("open_id"));
				smap.put("game_svr_entity", rs.getString("game_svr_entity"));
				smap.put("game_seq", rs.getString("game_seq"));
				smap.put("relay_svr_entity", rs.getString("relay_svr_entity"));
				listmap.add(smap);
            }
			return listmap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void update(String open_id,String i){
		System.out.println("update------"+open_id+"------");
		try {
			Statement statement=conn.createStatement();
			int result = statement.executeUpdate("update userinfo set is_grab="+i+" where open_id='"+open_id+"'");
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public ResultSet getData(String sql){
		try {
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void closs(){
		try {
			conn.close();
			System.out.println("链接已经关闭!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
