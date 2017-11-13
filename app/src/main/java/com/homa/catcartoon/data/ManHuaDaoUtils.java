package com.homa.catcartoon.data;

import com.homa.catcartoon.base.MyApplication;
import com.homa.catcartoon.data.greendao.ManHuaDao;

import java.util.List;

/**
 * Created by handsome on 2016/4/19.
 */
public class ManHuaDaoUtils {

    /**
     * 添加数据
     *.
     * @param manHua
     */
    public static void insertManhua(ManHua manHua) {
        MyApplication.getInstance().getDaoSession().getManHuaDao().insert(manHua);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteManhua(long id) {
        MyApplication.getInstance().getDaoSession().getManHuaDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param manHua
     */
    public static void updateManhua(ManHua manHua) {
        MyApplication.getInstance().getDaoSession().getManHuaDao().update(manHua);
    }

    /**
     * 查询单个
     *
     * @return
     */
    public static ManHua queryManhua(String name) {
        return  MyApplication.getInstance().getDaoSession().getManHuaDao().queryBuilder().where(ManHuaDao.Properties.Title.eq(name)).unique();
    }
    /**
     * 查询所有的数据
     *
     * @return
     */
    public static List<ManHua> queryAll() {
        return  MyApplication.getInstance().getDaoSession().getManHuaDao().loadAll();
    }
    /**
     * 查询所有的数据
     *
     * @return
     */
    public static List<ManHua> queryPage(int page,int now) {
        return  MyApplication.getInstance().getDaoSession().getManHuaDao().queryBuilder().orderDesc(ManHuaDao.Properties.Modifytime).limit(page).offset(page*now).list();
    }
}
