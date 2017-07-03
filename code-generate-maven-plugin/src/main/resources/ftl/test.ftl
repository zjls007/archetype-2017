<#assign testPackage=config['test.package']!('') />
<#assign testExtends=config['test.extends']!('') />
<#assign dao=(beanName?uncap_first!) + "DAO" />
<#if (!testExtends?contains(testPackage) && testPackage != '') || (testPackage == '' && testExtends?contains("."))>
import ${testExtends};
</#if>
<#if (config['dao.package'])?? && ((config['dao.package'])?length gt 0)>
import ${(config['dao.package'])!}.${beanName!}DAO;
</#if>
<#if (config['model.package'])?? && ((config['model.package'])?length gt 0)>
import ${(config['model.package'])!}.${beanName!};
</#if>
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
<#list importTypeList as item>
import ${item!};
</#list>

/**
 * Created by ${auth!} on ${(date?string("yyyy-MM-dd HH:mm:ss"))!}.
 */
public class ${beanName!}ServiceTest extends JunitSpringContext {

    @Resource
    private ${beanName!}DAO ${dao!};

    private ${beanName!} getEntity() {
        ${beanName!} entity = new ${beanName!}();

        <#list setMethodInvokeStrList as item>
        ${item}
        </#list>
        return entity;
    }

    @Test
    public void insert() {
        ${beanName!} entity = getEntity();
        int result = ${dao!}.insert(entity);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void batchInsert() {
        List<${beanName!}> list = new ArrayList<${beanName!}>();
        list.add(getEntity());
        int result = ${dao!}.batchInsert(list);
        Assert.assertEquals(result, list.size());
    }

    @Test
    public void delete() {
        int result = ${dao!}.delete(null);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void batchDelete() {
        List<Long> ids = Arrays.asList(new Long[] {null});
        int result = ${dao!}.batchDelete(ids);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void update() {
        ${beanName!} entity = getEntity();
        int result = ${dao!}.update(entity);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void getBy${primaryKeyPropertyName?cap_first!}() {
        ${beanName!} entity = ${dao!}.getBy${primaryKeyPropertyName?cap_first!}(null);
    }

    @Test
    public void getBy${primaryKeyPropertyName?cap_first!}List() {
        List<Long> ids = Arrays.asList(new Long[] {null});
        List<${beanName!}> list = ${dao!}.getBy${primaryKeyPropertyName?cap_first!}List(ids);
    }

}
