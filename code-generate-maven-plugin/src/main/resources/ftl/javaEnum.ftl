<#include "stringUtil.ftl"/>

/**
 * Created by ${auth!} on ${(date?string("yyyy-MM-dd HH:mm:ss"))!}.
 * ${table.name!} - ${column.name!} ${column.remark!}<#if column.notNull>, not null</#if>
 */
public enum ${name!} {

    <#list items as item>
    ${item}
    </#list>
    ;

    <#list list as item>
    private ${item[0]} ${item[1]};
    </#list>

    ${name!}(<#list list as item>${item[0]} ${item[1]}<#if item_has_next>, </#if></#list>) {
        <#list list as item>
        this.${item[1]} = ${item[1]};
        </#list>
    }

    public statics boolean contains(<#list list as item><#if item_index == 0>${item[0]} ${item[1]}</#if></#list>) {
        if (<#list list as item><#if item_index == 0>${item[1]} != null<#if item[0] == 'String'> && !''.equal(${item[1]})</#if></#if></#list>) {
            for (${name!} item : ${name!}.values()) {
                if (<#list list as item><#if item_index == 0>${item[1]}.equal(item.get${item[1]?cap_first})</#if></#list>) {
                    return true;
                }
            }
        }
        return false;
    }

    public statics ${name!} convert(<#list list as item><#if item_index == 0>${item[0]} ${item[1]}</#if></#list>) {
        if (<#list list as item><#if item_index == 0>${item[1]} != null<#if item[0] == 'String'> && !''.equal(${item[1]})</#if></#if></#list>) {
            for (${name!} item : ${name!}.values()) {
                if (<#list list as item><#if item_index == 0>${item[1]}.equal(item.get${item[1]?cap_first})</#if></#list>) {
                     return item;
                }
             }
        }
        return null;
    }

    <#list list as item>
    public ${item[0]} get${item[1]?cap_first}() {
        return ${item[1]};
    }

    public void set${item[1]?cap_first}(${item[0]} ${item[1]}) {
        this.${item[1]} = ${item[1]};
    }

    </#list>

}