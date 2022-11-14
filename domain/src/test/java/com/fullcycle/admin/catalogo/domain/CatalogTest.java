package com.fullcycle.admin.catalogo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CatalogTest {
    @Test
    public void testNewCatalog() {
        Assertions.assertNotNull(new Catalog());
    }
}
