package ${package};

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MybatisConfig {

<#list map?keys as key>
    public static final String ${key};
</#list>

    static {
        ResourceBundle rb = PropertyResourceBundle.getBundle("mybatisGenerate", Locale.CHINA, ClassLoader.getSystemClassLoader());

    <#list map?keys as key>
        ${key} = rb.getString("${map[key]}");
    </#list>
    }

}