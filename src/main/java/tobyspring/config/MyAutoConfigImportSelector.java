package tobyspring.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    // BeanClassLoaderAware 를 implements 한후 method를 구현하면 spring container 가 자동으로 해주는데 이런식으로 해도 자동으로 해줌.
    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // classLoader : 클래스path에서 리소스를 읽어올때는 파일 같은거 읽어올때는 classLoader를 사용함

        List<String> autoConfigs = new ArrayList<>();

        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate -> autoConfigs.add(candidate));
        return autoConfigs.toArray(new String[0]);

        //        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
//        return autoConfigs.stream().toArray(String[]::new);
//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

    }
}
