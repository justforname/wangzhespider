package main;

import java.util.HashMap;
import java.util.List;

public class UserInfo {
	public String nick_name;
	public String game_name;
	public String open_id;
	public String zone_area_id;
	public String ladder_score;//平均评分
	public String rank_desc;//当前级别
	public String rank_star;//当前级别几星
	public String service_name;//游戏区
	public String winning_percentage;//胜率
	public String win_desc;//超越玩家
	public String head_img_url;//用户头像
	public List<HashMap<String, String>> battle_listmap;
	
}
