package app.example.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PreferencesManagerTest {

    private lateinit var preferencesManager: PreferencesManager
    private val dataStore: DataStore<Preferences> = mockk(relaxed = true)

    @Before
    fun setup() {
        preferencesManager = PreferencesManager(dataStore)
    }

    @Test
    fun `tokenFlow emits saved token`() = runTest {
        // given
        val testToken = "test_token"
        val mockPreferences = mockk<Preferences> {
            every { get(PreferencesManager.PreferencesKeys.TOKEN) } returns testToken
        }

        every { dataStore.data } returns flowOf(mockPreferences)

        preferencesManager = PreferencesManager(dataStore)

        // when
        val emittedToken = preferencesManager.tokenFlow.firstOrNull()

        // then
        assertEquals(testToken, emittedToken)
    }


    @Test
    fun `tokenFlow emits null when no token is saved`() = runTest {
        // given
        every { dataStore.data } returns flowOf(emptyPreferences())

        // when
        val emittedToken = preferencesManager.tokenFlow.firstOrNull()

        // then
        assertEquals(null, emittedToken)
    }

    @Test
    fun `tokenFlow handles IOException`() = runTest {
        // given
        every { dataStore.data } returns flow { throw IOException("Test IO Exception") }

        // when
        val emittedToken = preferencesManager.tokenFlow.firstOrNull()

        // then
        assertEquals(null, emittedToken)
    }
}






