package com.cy.common;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;

import java.util.List;

/**
 * Created by zxj on 2017/4/28.
 */
public class DaoJavaClientGenerator extends AbstractJavaClientGenerator {
    public DaoJavaClientGenerator(boolean requiresXMLGenerator) {
        super(requiresXMLGenerator);
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return null;
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        return null;
    }
}
