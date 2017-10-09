package com.mcourse.module.system.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.frame.db.base.query.OrderBy;
import com.mcourse.frame.db.base.query.Where;
import com.mcourse.module.system.model.McDictionary;

/**
 * DictionaryServiceImpl.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends BaseServiceImpl<McDictionary> implements IDictionaryService {

	@Resource(name = "dictionaryDao")
	@Override
	public void setBaseDao(IBaseDao<McDictionary> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public List<McDictionary> getDictByTypeid(Long typeid) throws SQLException {
		Where where = new Where.Builder().EQ("typeid", typeid).EQ("enable", true).OrderBy("sort", OrderBy.ASC).build();
		return findByWhere(where);
	}

}
