package ${packageName!};

<#list importList as item>
${item}
</#list>

/**
* Created by ${auth!} on ${date!}.
*/
public class ${modelName!} {

<#list columnList as item>
    /** ${item.remark} */
    private ${item.type} ${item.name};
</#list>

<#list columnList as item>
public ${item.type} get${item.name}() {
return ${item.name};
}

public void setName(${item.type} ${item.name}) {
this.${item.name} = ${item.name};
}
</#list>



}
