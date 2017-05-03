package com.cy.plugin;

import com.cy.resolver.CodeGenerateResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


@Mojo( name = "touch", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class CodeGenerateMojo extends AbstractMojo {

    @Parameter( defaultValue = "${project.basedir}/src/main/resources/code-generate.properties", property = "configFile", required = true )
    private File configFile;

    @Parameter( defaultValue = "${project.basedir}", property = "baseDir", required = true )
    private File baseDir;

    public void execute() throws MojoExecutionException, MojoFailureException {
        Properties p = new Properties();
        try {
            p.load(new FileReader(configFile));
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件出错", e);
        }
        CodeGenerateResolver resolver = new CodeGenerateResolver(p, baseDir);
        resolver.generate();
    }

}
