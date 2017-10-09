package com.mcourse.module.system.service;

import java.sql.SQLException;
import java.util.List;

import com.mcourse.frame.base.service.IBaseService;
import com.mcourse.module.system.model.McDictionary;

/**
 * IDictionaryService.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
public interface IDictionaryService extends IBaseService<McDictionary> {
	/**
	 * 根据字典类型id获取数据字段集合
	 * 
	 * @param typeid
	 * @return
	 * @throws SQLException
	 */
	public List<McDictionary> getDictByTypeid(Long typeid) throws SQLException;
}
