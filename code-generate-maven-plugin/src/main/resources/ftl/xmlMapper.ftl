<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${dto.daoFullName!}">

    <resultMap id="BaseResultMap" type="${dto.modelFullName!}">
        <#if dto.primaryKeyName??>
        <id column="${dto.primaryKeyName}" property="${nameResolver.getFieldName(dto.primaryKeyName)}" />
        </#if>
        <#if dto.noIdColunmList?? && (dto.noIdColunmList?size > 0)>
        <#list dto.noIdColunmList as item>
        <result column="${item}" property="${nameResolver.getFieldName(item)}" />
        </#list>
        </#if>
    </resultMap>

    <sql id="Base_Column_List">
        <#nt><@columnList/>
    </sql>

    <insert id="insert" parameterType="${dto.modelFullName!}">
        INSERT INTO ${dto.tableName}
        <#nt>(<@noIdColunmList/>)
        VALUES
        <#nt>(<@noIdColunmListValue prefix=""/>)
    </insert>

    <insert id="batchInsert">
        INSERT INTO ${dto.tableName}
        <#nt>(<@noIdColunmList/>)
        VALUES
        <foreach collection="list" item="item" separator=",">
            <#nt>(<@noIdColunmListValue prefix="item"/>)
        </foreach>
    </insert>

    <delete id="delete">
        DELETE FROM ${dto.tableName} where <@idEqual/>
    </delete>

    <delete id="batchDelete">
        DELETE FROM ${dto.tableName} where ${dto.primaryKeyName} in
        <foreach collection="list" open="(" close=")" separator="," item="item">
        ${'#'}{item}
        </foreach>
    </delete>

    <select id="selectById" resultMap="BaseResultMap" parameterType="${dto.modelFullName!}">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${dto.tableName} WHERE <@idEqual/>
    </select>

    <update id="update" parameterType="${dto.modelFullName!}" >
        update ${dto.tableName}
        <set>
            <#if dto.noIdColunmList?? && (dto.noIdColunmList?size > 0)>
            <#list dto.noIdColunmList as item>
            <if test="${nameResolver.getFieldName(item)} != null" >
                ${item} = ${nameResolver.getFieldName(item)},
            </if>
            </#list>
            </#if>
        </set>
        <#nt>where <@idEqual/>
    </update>

</mapper>

<#macro columnList>
    <#if dto.columnList?? && (dto.columnList?size > 0)>
        <#list dto.columnList?chunk(10) as row>
            <#list row as item>
                <#t>${item}
                <#t><#if item_has_next || row_has_next>, </#if>
            </#list>
        </#list>
    </#if>
</#macro>
<#macro noIdColunmList>
    <#if dto.noIdColunmList?? && (dto.noIdColunmList?size > 0)>
        <#list dto.noIdColunmList?chunk(10) as row>
            <#list row as item>
                <#t>${item}
                <#t><#if item_has_next || row_has_next>, </#if>
            </#list>
        </#list>
    </#if>
</#macro>
<#macro noIdColunmListValue prefix>
    <#if dto.noIdColunmList?? && (dto.noIdColunmList?size > 0)>
        <#list dto.noIdColunmList?chunk(10) as row>
            <#list row as item>
                <#t>${'#'}{<#if prefix?? && (prefix?length > 0)>${prefix}.</#if>
                <#t>${nameResolver.getFieldName(item)}}
                <#t><#if item_has_next || row_has_next>, </#if>
            </#list>
        </#list>
    </#if>
</#macro>
<#macro idEqual>
    <#if dto.primaryKeyName?? && (dto.primaryKeyName?length > 0)>
        <#nt>${dto.primaryKeyName} = ${'#'}{${nameResolver.getFieldName(dto.primaryKeyName)}}<#t>
    </#if>
</#macro>