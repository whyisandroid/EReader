package com.ereader.client;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

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
}
