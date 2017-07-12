package com.cy.plugin;

import com.cy.util.ClassUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;


@Mojo( name = "printMethodInvoke", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class PrintMethodInvokeMojo extends AbstractMojo {

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
        String source = p.getProperty("class.source");
        String sourceAlias = p.getProperty("class.sourceAlias");
        String target = p.getProperty("class.target");
        String targetAlias = p.getProperty("class.targetAlias");

        Class sourceClass = null;
        Class targetClass = null;
        if (isEmpty(source)) {
            System.out.println("请配置【class.source】");
        } else if (isEmpty(target)) {
            sourceClass = getClazz(source);
        } else if (!isEmpty(target)) {
            sourceClass = getClazz(source);
            targetClass = getClazz(target);
        }
        if (isEmpty(sourceAlias)) {
            sourceAlias = getAlias(sourceClass.getSimpleName());
        }
        if (isEmpty(targetAlias)) {
            targetAlias = getAlias(targetClass.getSimpleName());
        }
        print(sourceClass, sourceAlias, targetClass, targetAlias);
    }

    private void print(Class sourceClass, String sourceAlias, Class targetClass, String targetAlias) {
        if (targetClass == null) {
            List<String> list = ClassUtil.getSetMethodInvoke(sourceClass, sourceAlias);
            for (String s : list) {
                System.out.println(s);
            }
        } else {
            List<String> list = ClassUtil.setBySameGet(sourceClass, sourceAlias, targetClass, targetAlias);
            for (String s : list) {
                System.out.println(s);
            }
        }
    }

    private String getAlias(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString().toLowerCase();
    }

    private boolean isEmpty(String str) {
        return  str == null || str.equals("");
    }

    public Class getClazz(String name) {
        try {
            return classLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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

    private void initClassLoader() throws MojoExecutionException {
        try {
            List<String> classpathElements = mavenProject.getCompileClasspathElements();
            classpathElements.add(mavenProject.getBuild().getOutputDirectory());
            classpathElements.add(mavenProject.getBuild().getTestOutputDirectory());
            URL urls[] = new URL[classpathElements.size()];

            for (int i = 0; i < classpathElements.size(); ++i) {
                urls[i] = new File((String) classpathElements.get(i)).toURI().toURL();
            }
            classLoader = new URLClassLoader(urls, getClass().getClassLoader());
        } catch (Exception e) {
            throw new MojoExecutionException("Couldn't create a classloader.", e);
        }
    }

}
