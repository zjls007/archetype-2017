package ${dto.packageName!};

<#if dto.importList?? && (dto.importList?size > 0)>
<#list dto.importList as item>
${item}
</#list>

</#if>
/**
 * Created by ${dto.auth!} on ${dto.date!}.
 */
public class ${nameResolver.getJavaClassName(dto.modelName)} {

<#if dto.columnList?? && (dto.columnList?size > 0)>
<#list dto.columnList as item>
    <#if item.remark?? && (item.remark?length > 0)>
    /** ${item.remark} */
    </#if>
    private ${javaTypeResolver.getType(item.type)} ${nameResolver.getFieldName(item.name)};

</#list>
</#if>
<#if dto.columnList?? && (dto.columnList?size > 0)>
<#list dto.columnList as item>
    public ${javaTypeResolver.getType(item.type)} ${nameResolver.getGetMethodName(item.name)}() {
        return ${nameResolver.getFieldName(item.name)};
    }

    public void ${nameResolver.getSetMethodName(item.name)}(${javaTypeResolver.getType(item.type)} ${nameResolver.getFieldName(item.name)}) {
        this.${nameResolver.getFieldName(item.name)} = ${nameResolver.getFieldName(item.name)};
    }

</#list>
</#if>
}
