<#include "stringUtil.ftl"/>
<@strExist source=config['model.package'] trueVal='package '/>${(config['model.package'])!}<@strExist source=config['model.package'] trueVal=';'/>

import java.io.Serializable;
<#list importTypeList as item>
import ${item!};
</#list>

/**
 * Created by ${auth!} on ${(date?string("yyyy-MM-dd HH:mm:ss"))!}.
 * ${tableName!}-${tableRemark!}
 */
public class ${beanName!} implements Serializable {

<#list propertyList as item>
    /** ${item.remark!}<#if item.notNull>, 必填</#if>*/
    private ${item.typeName!} ${item.propertyName!};

</#list>
<#list propertyList as item>
    public ${item.typeName!} ${item.getMethodName!}() {
        return ${item.propertyName!};
    }

    public void ${item.setMethodName!}(${item.typeName!} ${item.propertyName!}) {
        this.${item.propertyName!} = ${item.propertyName!};
    }

</#list>
}