package com.example.simplepasswordmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a password entry in the database.
 *
 * @property id The unique identifier for the password entry (auto-generated).
 * @property accountName The name of the account or service (e.g., "Google", "GitHub").
 * @property username The username or email used for the account.
 * @property password The password for the account.
 */
@Entity(tableName = "passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountName: String,
    val username: String,
    val password: String
)
