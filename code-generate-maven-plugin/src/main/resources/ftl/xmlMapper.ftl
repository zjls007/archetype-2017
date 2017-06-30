<#include "stringUtil.ftl"/>
<#assign daoPackage=config['dao.package']/>
<#assign modelPackage=config['model.package']/>
<#assign updateTime=(config['mapper.updateTime'])!/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="<@daoFullName/>">

    <resultMap id="BaseResultMap" type="<@modelFullName/>">
        <@resultMap/>
    </resultMap>

    <sql id="Base_Column_List">
        <@colunmList/>
    </sql>

    <sql id="Base_Alias_Column_List">
        <@colunmList prefix='abc'/>
    </sql>

    <insert id="insert" parameterType="<@modelFullName/>" useGeneratedKeys="true" keyColumn="${primaryKeyColumnName!}" keyProperty="${primaryKeyPropertyName!}">
        INSERT INTO ${tableName!}
        <@noIdColunmList/>
        VALUES
        <@noIdColunmListValue/>
    </insert>

    <insert id="batchInsert">
        INSERT INTO ${tableName!}
        <@noIdColunmList/>
        VALUES
        <foreach collection="list" item="item" separator=",">
        <@noIdColunmListValue prefix="item"/>
        </foreach>
    </insert>

    <delete id="delete">
        DELETE FROM ${tableName!} where <@idEqual/>
    </delete>

    <delete id="batchDelete">
        DELETE FROM ${tableName!} where ${primaryKeyColumnName!} in
        <foreach collection="list" open="(" close=")" separator="," item="item">
            ${'#'}{item}
        </foreach>
    </delete>

    <update id="update" parameterType="<@modelFullName/>" >
        update ${tableName!}
        <set>
        <#list propertyList as item>
        <#if primaryKeyPropertyName?? && (item.propertyName == primaryKeyPropertyName)>
        <#elseif updateTime?? && (updateTime?length gt 0) && (updateTime == item.columnName)>
            ${item.columnName!} = ${'#'}{${item.propertyName!}}<#if item_has_next>,</#if>
        <#else>
            <if test="${item.propertyName!} != null" >
                ${item.columnName!} = ${'#'}{${item.propertyName!}},
            </if>
        </#if>
        </#list>
        </set>
        where <@idEqual/>
    </update>

    <select id="getBy${primaryKeyPropertyName?cap_first!}" resultMap="BaseResultMap" parameterType="<@modelFullName/>">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName!} WHERE <@idEqual/>
    </select>

    <select id="getBy${primaryKeyPropertyName?cap_first!}List" resultMap="BaseResultMap" parameterType="<@modelFullName/>">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName!} WHERE ${primaryKeyColumnName!} IN
        <foreach collection="list" open="(" close=")" separator="," item="item">
            ${"#"}{item}
        </foreach>
    </select>

    <#list propertyList as item>
    <#if item.singleUnqKey??>
    <select id="getBy${item.propertyName?cap_first!}" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName!} WHERE ${item.columnName!} = ${'#'}{${item.propertyName!}}
    </select>

    </#if>
    </#list>
</mapper>

<#macro daoFullName>
    <#t>${daoPackage!}<@strExist source=daoPackage trueVal='.'/>${beanName!}DAO
</#macro>
<#macro modelFullName>
    <#t>${modelPackage!}<@strExist source=daoPackage trueVal='.'/>${beanName!}
</#macro>

<#macro resultMap>
    <#list propertyList as item>
        <#if item.primaryKey??>
        <id column="${item.columnName!}" property="${item.propertyName!}" />
        <#else>
        <result column="${item.columnName!}" property="${item.propertyName!}" />
        </#if>
    </#list>
</#macro>
<#macro colunmList prefix=''>
    <#list propertyList?chunk(10) as row>
        <#list row as item>${prefix}<@strExist source=prefix trueVal='.'/>${item.columnName!}<#if item_has_next || row_has_next>, </#if></#list>
    </#list>
</#macro>
<#macro noIdColunmList>
    <#list propertyList?chunk(10) as row>
        <#if row_index=0>(</#if><#list row as item><#if !item.primaryKey??>${item.columnName!}<#if item_has_next || row_has_next>, </#if></#if></#list><#if !row_has_next>)</#if>
    </#list>
</#macro>
<#macro noIdColunmListValue prefix=''>
    <#list propertyList?chunk(10) as row>
        <#if prefix!=''>   </#if><#if row_index=0>(</#if><#list row as item><#if !item.primaryKey??>${'#'}{${prefix!}<@strExist source=prefix trueVal='.'/>${item.propertyName!}}<#if item_has_next || row_has_next>, </#if></#if></#list><#if !row_has_next>)</#if>
    </#list>
</#macro>
<#macro idEqual>
    <#if primaryKeyColumnName?? && (primaryKeyColumnName?length > 0)>
        <#nt>${primaryKeyColumnName!} = ${'#'}{${primaryKeyPropertyName!}}<#t>
    </#if>
</#macro>