package ${serviceImplPackageName};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import ${modelClass};

/**
 * ${serviceImplFileName}
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("${modelNameLowercase}Service")
public class ${serviceImplClassSimpleName} extends BaseServiceImpl<${modelClassName}> implements ${serviceClassSimpleName} {

	@Resource(name = "${modelNameLowercase}Dao")
	@Override
	public void setBaseDao(IBaseDao<${modelClassName}> baseDao) {
		super.setBaseDao(baseDao);
	}

}
