<#include "stringUtil.ftl"/>
<#assign auth=(config['auth'])!/>
<@strExist source=config['dao.package'] trueVal='package '/>${(config['dao.package'])!}<@strExist source=config['dao.package'] trueVal=';'/>

<#if (config['model.package'])?? && ((config['model.package'])?length gt 0)>
import ${(config['model.package'])!}.${beanName!};
</#if>
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${auth!} on ${(date?string("yyyy-MM-dd HH:mm:ss"))!}.
 */
@Repository
public interface ${beanName!}DAO {

    int insert(${beanName!} entity);

    int batchInsert(List<${beanName!}> list);

    int delete(${primaryKeyType!} ${primaryKeyPropertyName!});

    int batchDelete(List<${primaryKeyType!}> list);

    int update(${beanName!} entity);

    ${beanName!} getBy${primaryKeyPropertyName?cap_first!}(${primaryKeyType!} ${primaryKeyPropertyName!});

    List<${beanName!}> getBy${primaryKeyPropertyName?cap_first!}List(List<${primaryKeyType!}> list);

}
