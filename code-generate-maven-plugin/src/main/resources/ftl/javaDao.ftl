<#include "stringUtil.ftl"/>
<#assign auth=(config['auth'])!/>
<@strExist source=config['dao.package'] trueVal='package '/>${(config['dao.package'])!}<@strExist source=config['dao.package'] trueVal=';'/>

<#if (config['model.package'])?? && ((config['model.package'])?length gt 0)>
import ${(config['model.package'])!}.${beanName!};
</#if>
import org.springframework.stereotype.Repository;
<#if daoShowParam>import org.apache.ibatis.annotations.Param;</#if>

import java.util.List;

/**
 * Created by ${auth!} on ${(date?string("yyyy-MM-dd HH:mm:ss"))!}.
 */
@Repository
public interface ${beanName!}DAO {

    int insert(${beanName!} entity);

    int batchInsert(List<${beanName!}> list);

    int deleteBy${primaryKeyPropertyName?cap_first!}(${primaryKeyType!} ${primaryKeyPropertyName!});

    int batchDelete(List<${primaryKeyType!}> list);

    int updateBy${primaryKeyPropertyName?cap_first!}Selective(${beanName!} entity);

    int updateBy${primaryKeyPropertyName?cap_first!}(${beanName!} entity);

    ${beanName!} getBy${primaryKeyPropertyName?cap_first!}(${primaryKeyType!} ${primaryKeyPropertyName!});

    List<${beanName!}> getBy${primaryKeyPropertyName?cap_first!}List(List<${primaryKeyType!}> list);

    List<${beanName!}> list(${beanName!} entity);

    <#list uniKeyList as list>
    ${beanName!} getBy<#list list as item>${item.propertyName?cap_first!}<#if item_has_next>And</#if></#list>(<#list list as item><#if (list?size>1)>@Param("${item.propertyName!}") </#if>${item.typeName!} ${item.propertyName!}<#if item_has_next>, </#if></#list>);

    int countBy<#list list as item>${item.propertyName?cap_first!}<#if item_has_next>And</#if></#list>(<#list list as item><#if (list?size>1)>@Param("${item.propertyName!}") </#if>${item.typeName!} ${item.propertyName!}<#if item_has_next>, </#if></#list>);

    </#list>
    <#list indexKeyList as list>
    List<${beanName!}> listBy<#list list as item>${item.propertyName?cap_first!}<#if item_has_next>And</#if></#list>(<#list list as item>${item.typeName!} ${item.propertyName!}<#if item_has_next>, </#if></#list>);

    </#list>
}
