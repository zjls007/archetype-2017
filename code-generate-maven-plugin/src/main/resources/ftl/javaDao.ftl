package ${dto.daoPackage!};

import ${dto.modelPackage!}.${dto.modelName!};
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${dto.auth!} on ${dto.date!}.
 */
@Repository
public interface ${dto.modelName!}DAO {

    int insert(${dto.modelName!} entity);

    int batchInsert(List<${dto.modelName!}> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(${dto.modelName!} entity);

    ${dto.modelName!} selectById(Long id);

<#if uniKeyList?? && (uniKeyList?size>0)>
    <#list uniKeyList as item>
    ${dto.modelName!} selectBy${nameResolver.getJavaClassName(item.name)}(${javaTypeResolver.getType(item.type)} ${nameResolver.getFieldName(item.name)});

    </#list>
</#if>
}
