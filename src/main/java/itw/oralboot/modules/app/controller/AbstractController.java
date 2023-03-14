/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package itw.oralboot.modules.app.controller;

import itw.oralboot.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *基于TheadLocal封装工具类，用户保存和获取用户当前id
 * @author Mark sunlightcs@gmail.com
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}

	private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

	/**
	 * 设置值
	 * @param id
	 */
	public static void setCurrentId(Long id){
		threadLocal.set(id);
	}

	/**
	 * 获取值
	 * @return
	 */
	public static Long getCurrentId(){
		return threadLocal.get();
	}
}
