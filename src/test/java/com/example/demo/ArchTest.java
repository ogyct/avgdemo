package com.example.demo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {
    @Test
    void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.example.demo");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..service..")
                .should().accessClassesThat().resideInAPackage("..controller..")
                .orShould().accessClassesThat().resideInAPackage("..repository..");
        rule.check(importedClasses);
    }
}
