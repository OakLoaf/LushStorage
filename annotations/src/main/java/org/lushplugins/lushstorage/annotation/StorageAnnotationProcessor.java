package org.lushplugins.lushstorage.annotation;

import com.google.auto.service.AutoService;
import javassist.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@AutoService(Processor.class)
public class StorageAnnotationProcessor extends AbstractProcessor {

    public StorageAnnotationProcessor() {
        super();
        System.out.println("Storage annotation processor has been constructed!");
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Storage annotation processor has been initialised!");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
//        return Set.of("*");
        return Set.of("org.lushplugins.lushstorage.annotation.StorageMethod");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Storage annotation processor is running!");

        for (Element element : roundEnv.getElementsAnnotatedWith(StorageMethod.class)) {
            ExecutableElement methodElement = (ExecutableElement) element;
            StorageMethod annotation = methodElement.getAnnotation(StorageMethod.class);

            String methodName = methodElement.getSimpleName().toString();
            String returnType = methodElement.getReturnType().toString();
            String parameters = methodElement.getParameters().toString();
            String storageClassName = annotation.storageClassName();

            System.out.println("Processing method: " + methodName + " in class: " + storageClassName);

            try {
                ClassPool pool = ClassPool.getDefault();
                CtClass storageClass = pool.get(storageClassName);

                String newMethodBody = String.format("""
                        public java.util.concurrent.CompletableFuture<%s> %s(%s) {
                            return java.util.concurrent.CompletableFuture.runAsync(() -> {
                                return %s(%s);
                            });
                        }
                        """,
                    returnType, methodName, parameters, methodName, parameters
                );

                CtMethod newMethod = CtMethod.make(newMethodBody, storageClass);
                storageClass.addMethod(newMethod);

                // Write the modified class to the generated sources directory
                String outputPath = processingEnv.getFiler().createSourceFile(storageClassName).toUri().getPath();
                storageClass.writeFile(outputPath);
            } catch (NotFoundException | IOException | javassist.CannotCompileException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
