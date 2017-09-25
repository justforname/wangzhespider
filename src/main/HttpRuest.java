package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class HttpRuest {

	private String uin;
	private String key;
	private String pass_ticket;
	private String cookie = "";
	private String uagent;
	private String url_1;
	private String url_2;
	private String downdir;
	
	public HttpRuest(){
		uin = Config.uin;
		key = Config.key;
		pass_ticket = Config.pass_ticket;
		uagent = Config.uagent;
		url_1 = Config.url_1;
		url_2 = Config.url_2;
		downdir = Config.downdir;
	}
	/**
	 * 获取用户信息
	 * @param open_id
	 * @param zone_area_id
	 */
	public String getUserinfo(String open_id,String zone_area_id){
		String urls = new StringBuilder(url_1).append("?").append("openid=").append(open_id).append("&zone_area_id=").append(zone_area_id).append("&uin=").append(uin).append("&key=").append(key).append("&pass_ticket=").append(pass_ticket).toString();
		OkHttpClient okhttp = new OkHttpClient();
		Request request = new Request.Builder().removeHeader("User-Agent").addHeader("User-Agent", uagent).addHeader("Cookie", cookie).url(urls).get().build();
		Call call = okhttp.newCall(request);
		try {
			Response res = call.execute();
			//System.out.println(res.body().string());
			return res.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 获取战绩信息
	 * @param game_svr_entity
	 * @param game_seq
	 * @param relay_svr_entity
	 * @param open_id
	 */
	public String getbattledetail(String game_seq, String game_svr_entity, String relay_svr_entity, String open_id){
		String urls = new StringBuilder(url_2).append("?").append("game_svr_entity=").append(game_svr_entity).append("&game_seq=").append(game_seq).append("&relay_svr_entity=").append(relay_svr_entity).append("&openid=").append(open_id).append("&uin=").append(uin).append("&key=").append(key).append("&pass_ticket=").append(pass_ticket).toString();
		OkHttpClient okhttp = new OkHttpClient();
		Request request = new Request.Builder().removeHeader("User-Agent").addHeader("User-Agent", uagent).addHeader("Cookie", cookie).url(urls).get().build();
		Call call = okhttp.newCall(request);
		try {
			Response res = call.execute();
			//System.out.println(res.body().string());
			return res.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 下载图片
	 * @param url
	 * @param name
	 */
	public void downLoadImg(String url,String name){
		OkHttpClient okhttp = new OkHttpClient();
		Request request = new Request.Builder().removeHeader("User-Agent").addHeader("User-Agent", uagent).url(url).get().build();
		Call call = okhttp.newCall(request);
		FileOutputStream fos = null;
		File file = new File(downdir);
		if(!file.exists()){
			file.mkdirs();
		}
		File imge = new File(downdir+name+".jpeg");
		try {
			InputStream is = call.execute().body().byteStream();
			fos = new FileOutputStream(imge);

			byte[] buf = new byte[2048];
			int len = 0;
			while((len=is.read(buf))!=-1){
				fos.write(buf, 0, len);
			}
			fos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {

		}
		
	}

}
