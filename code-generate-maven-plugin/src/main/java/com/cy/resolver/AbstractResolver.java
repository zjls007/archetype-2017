package com.cy.resolver;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxj on 2017/5/3.
 */
public abstract class AbstractResolver {

    protected Map<String, Object> getDataModel() throws TemplateModelException {
        Map<String, Object> root = new HashMap<String, Object>();
        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();

        TemplateHashModel javaTypeResolver = (TemplateHashModel) staticModels.get("com.cy.resolver.JavaTypeResolver");
        root.put("javaTypeResolver", javaTypeResolver);

        TemplateHashModel nameResolver = (TemplateHashModel) staticModels.get("com.cy.resolver.NameResolver");
        root.put("nameResolver", nameResolver);
        dataModel(root);
        return root;
    }

    protected abstract void dataModel(Map<String, Object> root);

}
