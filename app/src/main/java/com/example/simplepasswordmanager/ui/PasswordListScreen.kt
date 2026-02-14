package com.example.simplepasswordmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplepasswordmanager.data.PasswordEntity
import com.example.simplepasswordmanager.data.PasswordViewModel

@Composable
fun PasswordListScreen(viewModel: PasswordViewModel = viewModel()) {
    val passwords by viewModel.allPasswords.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Password")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(passwords) { password ->
                    PasswordItem(
                        password = password,
                        onDelete = { viewModel.deletePassword(password) }
                    )
                }
            }
        }

        if (showDialog) {
            AddPasswordDialog(
                onDismiss = { showDialog = false },
                onConfirm = { account, username, pwd ->
                    viewModel.insertPassword(
                        PasswordEntity(accountName = account, username = username, password = pwd)
                    )
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun PasswordItem(password: PasswordEntity, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = password.accountName, style = MaterialTheme.typography.titleMedium)
                Text(text = "User: ${password.username}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Pass: ${password.password}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun AddPasswordDialog(onDismiss: () -> Unit, onConfirm: (String, String, String) -> Unit) {
    var account by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Password") },
        text = {
            Column {
                TextField(value = account, onValueChange = { account = it }, label = { Text("Account Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(account, username, password) }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
