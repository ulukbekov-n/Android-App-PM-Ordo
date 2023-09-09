package com.example.pmordo.presentation.utils.extension

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun <T : Parcelable> Fragment.runWithArgumentsOrSkip(id: String, block: (arguments: T) -> Unit) =
    this.arguments?.getParcelable<T>(id)?.let(block) ?: Unit

fun Fragment.runWithArgumentsOrSkip(block: (bundle: Bundle) -> Unit) =
    this.arguments?.let(block) ?: Unit

/**
 * Показывает диалог асинхронно, т.е. диалог покажется не в момент вызова.
 * Если нужно показывать диалог в момент вызова, для избежания дублирования диалогов,
 * то использовать showNowOnlyOne(fragmentManager)
 */
fun DialogFragment.showOnlyOne(fragmentManager: FragmentManager) {
    if (fragmentManager.findFragmentByTag(this.javaClass.name) == null)
        this.show(fragmentManager, this.javaClass.name)
}

inline fun bindingLifecycleError(): Nothing = throw IllegalStateException(
    "This property is only valid between onCreateView and onDestroyView."
)
