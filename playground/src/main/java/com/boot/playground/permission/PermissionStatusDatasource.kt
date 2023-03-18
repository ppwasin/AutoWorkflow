package com.boot.playground.permission

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val DATASTORE_NAME = "permission"
private var permissionDataStore: DataStore<Preferences>? = null

fun providePopupDatastore(context: Context): DataStore<Preferences> =
	permissionDataStore
		?: PreferenceDataStoreFactory.create(
			corruptionHandler =
			ReplaceFileCorruptionHandler(
				produceNewData = { emptyPreferences() },
			),
			produceFile = { context.preferencesDataStoreFile(DATASTORE_NAME) },
		)
			.also { permissionDataStore = it }

class PermissionStatusDatastore(private val dataStore: DataStore<Preferences>) {
	private val isPermissionPromptShown =
		booleanPreferencesKey("isPermissionPromptShown")

	fun isUserAllow(): Flow<Boolean?> {
		return dataStore.data.map { it[isPermissionPromptShown] }
	}

	suspend fun setIsUserAllow(isAllow: Boolean) {
		dataStore.edit { it[isPermissionPromptShown] = isAllow }
	}
}

@Stable
interface PermissionStatus {
	val userActionWithSoftprompt: Boolean?
	fun update(isAllow: Boolean)
}

@Composable
fun rememberPermissionDatastore(context: Context): PermissionStatus {
	val datastore = remember {
		PermissionStatusDatastore(providePopupDatastore(context))
	}
	val coroutine = rememberCoroutineScope()

	val status = remember {
		object : PermissionStatus {
			override var userActionWithSoftprompt: Boolean? by mutableStateOf(null)
			override fun update(isAllow: Boolean) {
				coroutine.launch { datastore.setIsUserAllow(isAllow) }
			}
		}
	}

	LaunchedEffect(Unit) {
		datastore
			.isUserAllow()
			.onEach { status.userActionWithSoftprompt = it }
			.launchIn(this)
	}

	return status
}
