package com.ereader.client;

import java.util.List;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.ereader.client.entities.Book;
import com.ereader.client.entities.Login;
import com.ereader.client.entities.json.BookOnlyResp;
import com.ereader.client.entities.json.SubCategoryResp;
import com.ereader.common.net.AppSocketInterface;
import com.ereader.common.net.XUtilsSocketImpl;

/******************************************
 * 类描述： 程序入口类 类名称：CreditWealthApplication
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-2-13 下午2:09:22
 ******************************************/
public class EReaderApplication extends Application {

	/** 实例化 **/
	private static EReaderApplication instance;
	/** 网络链接 **/
	private static AppSocketInterface appSocket;


	public int curVersionCode; // 版本号
	public String curVersionName; // 版本名字

	private boolean login;// 登录情况
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	/**
	 * 方法描述：初始化
	 * 
	 * @author: why
	 * @time: 2014-2-14 下午3:46:04
	 */
	private void init() {
		instance = this;
		appSocket = new XUtilsSocketImpl();
		getCurrentVersion();
	}

	/**
	 * @return login : return the property login.
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * @param login
	 *            : set the property login.
	 */
	public void setLogin(boolean login) {
		this.login = login;
		if (!login) {
			//AppController.getController().getContext().clearBusinessData();
		}
	}

	/**
	 * 方法描述: 获取网络通信实例
	 * 
	 * @return
	 * @author: why
	 * @time: 2013-10-21 下午3:32:02
	 */
	public static AppSocketInterface getAppSocket() {
		return appSocket;
	}

	/**
	 * 方法描述：获取实例
	 * 
	 * @return
	 * @author: why
	 * @time: 2013-10-21 下午2:52:44
	 */
	public static EReaderApplication getInstance() {
		return instance;
	}

	/**
	 * 获取当前客户端版本信息
	 */
	private void getCurrentVersion() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
			curVersionName = info.versionName;
			curVersionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}

	public SubCategoryResp getCategroy() {
		return AppSharedPref.getInstance(this).getCategroy();
	}
	public void saveCategroy(SubCategoryResp sub) {
		AppSharedPref.getInstance(this).saveCategroy(sub);
	}
	
	
	/**
	  * 方法描述：通用的方法
	  * @param key
	  * @author:  ghf
	  * @time: 2015-6-8 下午7:03:35
	  */
	public void saveLocalInfoByKeyValue(String key,String value) {
		AppSharedPref.getInstance(this).saveLocalInfoByKeyValue(key,value);
	}
	/**
	  * 方法描述：通用的方法
	  * @param key
	  * @author:  ghf
	  * @time: 2015-6-8 下午7:03:35
	  */
	public String getLocalInfoByKeyValue(String key) {
		return AppSharedPref.getInstance(this).getLocalInfoByKeyValue(key);
	}


	public void saveLogin(Login data) {
		AppSharedPref.getInstance(this).saveLogin(data);
	}
	public Login getLogin() {
		return AppSharedPref.getInstance(this).getLogin();
	}

	public void saveBuyCar(BookOnlyResp resp) {
		AppSharedPref.getInstance(this).saveBuyCar(resp);
	
	}
	public BookOnlyResp getBuyCar() {
		return AppSharedPref.getInstance(this).getBuyCar();
	}
}
