package com.cy.plugin;

import com.cy.api.GenerateConfig;
import com.cy.resolver.CodeGenerateResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


@Mojo( name = "genCode", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class CodeGenerateMojo extends AbstractMojo {

    @Parameter( defaultValue = "${project.basedir}/src/main/resources/code-generate.properties", property = "configFile", required = true )
    private File configFile;

    @Parameter( defaultValue = "${project.basedir}", property = "baseDir", required = true )
    private File baseDir;

    public void execute() throws MojoExecutionException, MojoFailureException {
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(new FileInputStream(configFile), "GBK"));
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件出错", e);
        }
        GenerateConfig.init(p);
        CodeGenerateResolver resolver = new CodeGenerateResolver(p, baseDir);
        resolver.generate();
    }

}
