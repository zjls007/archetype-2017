package com.cy.plugin;

import com.cy.resolver.ConfigGeneratorResolver;
import com.cy.util.ClassUtil;
import com.cy.util.OrderedProperties;
import com.cy.util.PathUtil;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;


@Mojo( name = "congifGenerate", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class CongifGenerateMojo extends AbstractMojo {

    ClassLoader classLoader;

    @Parameter( defaultValue = "${project.basedir}/src/main/resources/code-generate.properties", property = "configFile", required = true )
    private File configFile;

    @Parameter( defaultValue = "${project.basedir}", property = "baseDir", required = true )
    private File baseDir;

    @Parameter( defaultValue =  "${project}", property = "mavenProject", required = true)
    private MavenProject mavenProject;

    public void execute() throws MojoExecutionException, MojoFailureException {
        initClassLoader();

        Properties p = getProperties();
        String genConfig = p.getProperty("gen.config");
        String configtargetProject = p.getProperty("config.targetProject");
        String configPackage = p.getProperty("config.package");
        String configFile = p.getProperty("config.file");
        if ("true".equalsIgnoreCase(genConfig) && !isEmpty(configFile)) {
            Properties op = new OrderedProperties();
            try {
                op.load(new FileReader(classLoader.getResource(configFile).getFile()));
            } catch (IOException e) {
                throw new RuntimeException(String.format("读取%s文件出错!", configFile), e);
            }
            ConfigGeneratorResolver.genernateFile(op, getPath(configtargetProject, configPackage), configPackage, configFile.replaceAll(".properties", ""));
        }
    }

    private String getPath(String configtargetProject, String configPackage) {
        StringBuilder path = new StringBuilder();
        path.append(baseDir.getAbsolutePath());
        if (!isEmpty(configtargetProject)) {
            PathUtil.backDir(path, configtargetProject);
        }
        if (!isEmpty(configPackage)) {
            PathUtil.backDir(path, configPackage.replaceAll("\\.", "/"));
        }
        path.append(File.separator);
        path.append("Config.java");
        return path.toString();
    }

    private Properties getProperties() {
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(new FileInputStream(configFile), "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件出错", e);
        }
        return p;
    }

    private boolean isEmpty(String str) {
        return  str == null || str.equals("");
    }

    private void initClassLoader() throws MojoExecutionException {
        try {
            List<Resource> resources = mavenProject.getResources();
            URL urls[] = new URL[resources.size()];
            int i = 0;
            for (Resource resource : resources) {
                urls[i++] = new File(resource.getDirectory()).toURI().toURL();
            }
            classLoader = new URLClassLoader(urls, getClass().getClassLoader());
        } catch (Exception e) {
            throw new MojoExecutionException("Couldn't create a classloader.", e);
        }
    }

}
