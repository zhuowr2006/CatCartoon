package com.homa.catcartoon.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * @author zwr
 *
 * Activity跳转方法
 */
public class ActivitySkipUtil {
	/**
	 * 获取Activity名字
	 * 
	 * @param cla 需要的页面
	 */
	public static String ActivityName(Class cla){
		return cla.getSimpleName();
	}	
	/**
	 * Activity跳转
	 * 
	 * @param ac 当前页面
	 * @param cla 需要跳转的页面
	 */
	public static void skipActivity(Activity ac, Class cla){
		Intent intent = new Intent(ac,cla);
		ac.startActivity(intent);
	}	
	/**
	 * Activity跳转
	 * 
	 * @param ac 当前页面
	 * @param cla 需要跳转的页面
	 */
	public static void skipActivity(Activity ac, Class cla, Bundle bundle){
		Intent intent = new Intent(ac,cla);
		intent.putExtras(bundle);
		ac.startActivity(intent);
	}	
	/**** Activity跳转并关闭跳转界面 ***/
	public static void skipActivityFinish(Activity ac, Class cla){
		Intent intent = new Intent(ac,cla);
		ac.startActivity(intent);
		ac.finish();
	}
	/**** ActivityForResult跳转界面 ***/
	public static void skipActivityForResult(Activity ac, Class cla, int requestCode){
		Intent intent = new Intent(ac,cla);
		ac.startActivityForResult(intent, requestCode);
	}
}

