package com.jantiojo.gweather.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jantiojo.gweather.ui.Routes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RouteDestinationDataStore @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    suspend fun setStartRouteDestination(route: String) {
        context.dataStore.edit {
            it[ROUTE_DESTINATION_KEY] = route
        }
    }

    val startRouteDestination: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[ROUTE_DESTINATION_KEY] ?: Routes.Login.route
        }

    companion object {
        private const val ROUTE_DESTINATION_PREFERENCE_NAME = "ROUTE_DESTINATION_PREFERENCE_NAME"
        private val Context.dataStore by preferencesDataStore(
            name = ROUTE_DESTINATION_PREFERENCE_NAME
        )
        private val ROUTE_DESTINATION_KEY = stringPreferencesKey("ROUTE_DESTINATION_KEY")
    }
}
