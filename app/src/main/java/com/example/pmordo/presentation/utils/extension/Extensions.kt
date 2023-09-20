package com.example.pmordo.presentation.utils.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


fun EditText.text() = this.text.toString()

fun TextView.text() {
    this.text.toString()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Activity?.hideKeyboard(view: View? = this?.currentFocus) {
    this ?: return
    view ?: return
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun EditText.validatePassword(): Boolean {
    val password = this.text.toString()
    return password.length >= 6
}

fun EditText.validateName(): Boolean {
    val name = this.text.toString()
    return name.length >= 2
}

fun EditText.validateLastName(): Boolean {
    val lastName = this.text.toString()
    return lastName.length >= 2
}

fun EditText.validateAge(): Boolean {
    val lastName = this.text.toString()
    return lastName.length in 1..2
}

fun EditText.validateEmail(): Boolean {
    val email = this.text.toString()
    return email.contains("@") && email.length > 7 && email.contains("gmail.com") || email.contains(
        "mail.ru"
    )
}
fun EditText.validateINN(): Boolean {
    val input = this.text.toString()
    return input.length == 14 && (input.startsWith("1") || input.startsWith("2"))
}

fun EditText.validateLogin(): Boolean {
    val login = this.text.toString()
    return login.length >= 6
}

