package main;

public  class Config {
	//请求相关参数
	//表示请求用户身份  使用时需要更改
	public static final String uin = "MjkzNTk3NzcwMQ%3D%3D";
	public static final String key = "e9572f452445d84845bf37cddb99537fe5fe931a2547286b8df818aaf7212bc52295941476443426bcb33f3633f23dc0c855f7aba23e34c2b5757a871ad61edfc9016ce7214503352d8f5ea7b37b8363";
	public static final String pass_ticket = "F%2Bm1vbCFAMLdGIXN7xZ6QiC74Dq4UHsgy%2FpiUBpSzv60KylmwhoAyoQhzffrzdWG"; //表示请求用户身份
	public static final String uagent = "Mozilla/5.0 (Linux; Android 5.1.1; D6603 Build/23.4.A.1.200; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043409 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN";
	
	//接口
	public static final String url_1 = "https://game.weixin.qq.com/cgi-bin/gamewap/getusermobagameindex";
	public static final String url_2 = "https://game.weixin.qq.com/cgi-bin/gamewap/getbattledetail";
	//头像下载路径
	public static final String downdir = "D://image//";
	
	//数据库连接参数
	public static final String host = "jdbc:mysql://127.0.0.1:3306/weiwangzhe?useUnicode=true&characterEncoding=utf8";
	public static final String name = "root";
	public static final String pword = "00000000";
	
	//自己用户数据
	public static final String ownopen_id = "owanlsg_qh8NW_S6_rkhnsRhyMfQ";
	public static final String ownzone_area_id = "4061";

	
}
