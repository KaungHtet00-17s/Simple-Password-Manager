package com.example.simplepasswordmanager.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val passwordDao = AppDatabase.getDatabase(application).passwordDao()
    val allPasswords: Flow<List<PasswordEntity>> = passwordDao.getAllPasswords()

    fun insertPassword(password: PasswordEntity) {
        viewModelScope.launch {
            passwordDao.insertPassword(password)
        }
    }

    fun deletePassword(password: PasswordEntity) {
        viewModelScope.launch {
            passwordDao.deletePassword(password)
        }
    }
}
