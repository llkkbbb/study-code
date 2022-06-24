package com.example.blogcode;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.thymeleaf.engine.IterationStatusVar;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * packageName    : com.example.blogcode
 * fileName       : ArchTests
 * author         : tkdwk567@naver.com
 * date           : 2022/06/17
 */

@AnalyzeClasses(packages = "com.example.blogcode")
public class ArchTests {

    @Test
    @DisplayName("간단한 테스트")
    void simple_test() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.example.blogcode");

        ArchRule myRule = classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

        myRule.check(classes);
    }

    @ArchTest
    ArchRule domainRule = noClasses().that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..web..");

    @ArchTest
    ArchRule classDependencyCheck = classes()
            .that().haveNameMatching(".*Controller")
            .should().onlyHaveDependentClassesThat().haveNameMatching(".*Service");

    @ArchTest
    LayeredArchitecture classAndPackageLayerIsolationCheck = layeredArchitecture()
            .layer("web").definedBy("..web..")
            .layer("service").definedBy("..service..")
            .layer("domain").definedBy("..domain..")
            .whereLayer("web").mayNotBeAccessedByAnyLayer()
            .whereLayer("service").mayOnlyBeAccessedByLayers("web")
            .whereLayer("domain").mayOnlyBeAccessedByLayers("service");
}
