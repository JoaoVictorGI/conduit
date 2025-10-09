package com.conduit;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTests {
    ApplicationModules modules = ApplicationModules.of(ConduitApplication.class);

    @Test
    void verifiesArchitecture() {
        IO.println(modules);
        modules.verify();
    }

    @Test
    void writeDocumentation() {
        new Documenter(modules).writeDocumentation();
    }
}
