package app.toque.core

import app.toque.core.recipes.McOnline
import app.toque.core.recipes.Recipe
import app.toque.core.recipes.SearchIndex
import app.toque.core.recipes.TextNorm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Recettes en espagnol (catalogue es-ES) : pluriels, mots vides et unités de portions. */
class SpanishTest {
    private fun t(s: String) = TextNorm.tokens(s, language = "es")

    @Test fun recipeLanguageFollowsInterface() {
        assertEquals("es-ES", McOnline.languageFor("es"))
        assertEquals("es-ES", McOnline.languageFor("es-419"))
    }

    @Test fun spanishPlurals() {
        assertEquals(t("limón"), t("limones"))
        assertEquals(t("nuez"), t("nueces"))
        assertEquals(t("tomate"), t("tomates"))
        assertEquals(t("huevo"), t("huevos"))
        assertEquals(t("pan"), t("panes"))
        assertEquals(t("dulce"), t("dulces"))
        assertEquals(listOf("pollo", "curry"), t("Pollo con curry"))
        assertEquals(listOf("crema", "calabaza"), t("Crema de calabaza"))
        // « sin » garde son sens (« sin gluten »).
        assertTrue("sin" in t("Bizcocho sin gluten"))
    }

    @Test fun frenchUnchanged() {
        assertEquals(listOf("pomme", "terre"), TextNorm.tokens("Pommes de terre"))
        assertEquals(listOf("gateau"), TextNorm.tokens("gâteaux"))
    }

    @Test fun spanishSearch() {
        fun r(id: String, title: String) = Recipe(id = id, title = title)
        val idx = SearchIndex(listOf(
            r("a", "Crema de calabaza"), r("b", "Pollo al limón"), r("c", "Galletas de nueces"), r("d", "Salsa de tomates asados"),
        ), language = "es")
        fun top(q: String) = idx.search(q).firstOrNull()?.entry?.id
        assertEquals("b", top("limones"))
        assertEquals("c", top("nuez"))
        assertEquals("c", top("galleta"))
        assertEquals("d", top("tomate"))
        assertEquals("a", top("calabasa"))
    }

    @Test fun servingUnits() {
        assertTrue(McOnline.isPeopleUnit("porciones"))
        assertTrue(McOnline.isPeopleUnit("Raciones"))
        assertTrue(McOnline.isPeopleUnit("personas"))
        assertTrue(McOnline.isPeopleUnit("portions"))
        assertFalse(McOnline.isPeopleUnit("trozos"))
    }
}
