package ${daoImplPackageName};

import org.springframework.stereotype.Repository;

import com.mcourse.frame.base.dao.BaseDaoImpl;
import ${modelClass};

/**
 * ${daoImplFileName}
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Repository("${modelNameLowercase}Dao")
public class ${daoImplClassSimpleName} extends BaseDaoImpl<${modelClassName}> implements ${daoClassSimpleName} {

}
