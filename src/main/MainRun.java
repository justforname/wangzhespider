package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainRun {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//执行前先注销 runner 执行 getM
		//获取一条自己数据后 在注销 getMe 执行runner
		int num = 1;//抓深度
		
		//getMe();
		for(int i=0;i<num;i++){
			runner();
		}
		
			
	}
	
	public static void runner() throws InterruptedException{
		List<HashMap<String, String>> listmap = new LinkedList<HashMap<String,String>>();
		Sqlink mysql = new Sqlink();
		Rules rules = new Rules();
		HttpRuest http = new HttpRuest();
		mysql.connetMsql();
		
//		listmap = mysql.getUser();//获取需要运行用户
//		System.out.println(listmap.size());
//		int unum = listmap.size();
//		for(int i=0;i<unum;i++){
//			List<HashMap<String, String>> a = rules.getPageuser(http.getbattledetail(listmap.get(i).get("game_seq"),listmap.get(i).get("game_svr_entity"),listmap.get(i).get("relay_svr_entity"),listmap.get(i).get("open_id")));
//			for(int j=0;j<a.size();j++){
//				iputUser(a.get(j).get("open_id"),a.get(j).get("zone_area_id"),mysql,http,rules);
//				System.out.println(a.get(j).get("open_id"));
//				
//			}
//			mysql.update(listmap.get(i).get("open_id"),"1");
//			System.out.println(unum-i);
//		}
		

		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		listmap = mysql.getUser();//获取需要运行用户
		System.out.println(listmap.size());
		for(int i=0;i<listmap.size();i++){
			List<HashMap<String, String>> a = rules.getPageuser(http.getbattledetail(listmap.get(i).get("game_seq"),listmap.get(i).get("game_svr_entity"),listmap.get(i).get("relay_svr_entity"),listmap.get(i).get("open_id")));
			String open_id = listmap.get(i).get("open_id");
			Runnable task = new Runnable() {
			    @Override
			    public void run() {
					for(int j=0;j<a.size();j++){
						iputUser(a.get(j).get("open_id"),a.get(j).get("zone_area_id"),mysql,http,rules);
						System.out.println(a.get(j).get("open_id"));
					}
			        try {
						//Thread.sleep(3000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        mysql.update(open_id,"1");
			    }
			    
			};
			threadPool.execute(task);
		}
		
		threadPool.shutdown();
		mysql.closs();
		
	}
	
	public static void getMe(){
		Sqlink msql = new Sqlink();
		Rules rules = new Rules();
		HttpRuest http = new HttpRuest();
		msql.connetMsql();
		UserInfo uinfo = new UserInfo();
		rules.filUserInfo(http.getUserinfo(Config.ownopen_id, Config.ownzone_area_id), uinfo);
		msql.insert(uinfo);
		//http.getbattledetail("1503846352","23368","2517566304", "owanlsmTFup0K5CnXo7_6FTuaiuQ");
	}
	
	public static void iputUser(String open_id,String zone_area_id,Sqlink msql,HttpRuest http,Rules rules){
		UserInfo uinfo = new UserInfo();
		rules.filUserInfo(http.getUserinfo(open_id, zone_area_id), uinfo);
		msql.insert(uinfo);
		if(!"/0".equals(uinfo.head_img_url))
			http.downLoadImg(uinfo.head_img_url, uinfo.open_id);
		
	}
	

}

class Data{
	public static List<HashMap<String, String>> a;
	
}
