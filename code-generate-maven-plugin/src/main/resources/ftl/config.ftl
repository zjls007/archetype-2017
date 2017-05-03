package ${package};

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Config {

<#list map?keys as key>
    public static final String ${key};
</#list>

    static {
        ResourceBundle rb = PropertyResourceBundle.getBundle("config");

    <#list map?keys as key>
        ${key} = rb.getString("${map[key]}");
    </#list>
    }

}