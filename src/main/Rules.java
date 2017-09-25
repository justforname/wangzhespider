package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Rules {
	JSONObject json;
	List<HashMap<String, String>> battle_listmap = new LinkedList<HashMap<String,String>>();
	
 	public void filUserInfo(String response, UserInfo userinfo){
		json = new JSONObject();
		json = JSONObject.fromObject(response);
		JSONObject uinfo = json.getJSONObject("user_info");
		
		userinfo.nick_name = uinfo.getString("nick_name");
		userinfo.game_name = uinfo.getString("game_name");
		userinfo.open_id = uinfo.getString("open_id");
		userinfo.zone_area_id = uinfo.getString("zone_area_id");
		userinfo.service_name = uinfo.getString("service_name");
		userinfo.rank_desc = uinfo.getString("rank_desc");
		userinfo.rank_star = uinfo.getString("rank_star");
		userinfo.winning_percentage = uinfo.getString("winning_percentage");
		userinfo.win_desc = uinfo.getString("win_desc");
		userinfo.head_img_url = uinfo.getString("head_img_url")+"/0";
		userinfo.ladder_score = uinfo.getString("ladder_score");
		
		System.out.println(uinfo.getString("head_img_url"));
		
		JSONArray battle_list = json.getJSONObject("battle_info").getJSONArray("battle_list");
		for(int i=0;i<battle_list.size();i++){
			JSONObject ijs = battle_list.getJSONObject(i);
			HashMap<String, String> cell = new HashMap<String,String>();
			cell.put("kda", ijs.getString("kda"));
			cell.put("game_type", ijs.getString("game_type"));
			cell.put("kill_cnt", ijs.getString("kill_cnt"));
			cell.put("dead_cnt", ijs.getString("dead_cnt"));
			cell.put("assist_cnt", ijs.getString("assist_cnt"));
			cell.put("game_seq", ijs.getString("game_seq"));
			cell.put("is_mvp", ijs.getString("is_mvp"));
			cell.put("is_friend", ijs.getString("is_friend"));
			cell.put("is_victory", ijs.getString("is_victory"));
			cell.put("game_svr_entity", ijs.getString("game_svr_entity"));
			cell.put("relay_svr_entity", ijs.getString("relay_svr_entity"));
			cell.put("dt_event_time", ijs.getString("dt_event_time"));
			
			battle_listmap.add(i, cell);
		}
		userinfo.battle_listmap = battle_listmap;	
	}
 	
 	public List<HashMap<String, String>> getPageuser(String response){
 		//System.out.println(response);
 		HashMap<String, String> map;
 		List<HashMap<String, String>> listmap = new LinkedList<HashMap<String, String>>();
 		JSONObject json = new JSONObject();
 		json = JSONObject.fromObject(response);
 		JSONArray jsonarry = json.getJSONArray("user_battle_detail");
 		for(int i=0;i<jsonarry.size();i++){
 			map = new HashMap<String,String>();
 			map.put("open_id", jsonarry.getJSONObject(i).getString("open_id"));
 			map.put("zone_area_id", jsonarry.getJSONObject(i).getString("zone_area_id"));
 			listmap.add(map);
 		}
 		return listmap;
 	}
}
