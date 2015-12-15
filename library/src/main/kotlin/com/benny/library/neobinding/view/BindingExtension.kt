package com.benny.library.neobinding.view

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.benny.library.neobinding.bind.*
import com.benny.library.neobinding.converter.*
import com.jakewharton.rxbinding.view.clicks
import com.jakewharton.rxbinding.view.enabled
import com.jakewharton.rxbinding.view.visibility
import com.jakewharton.rxbinding.widget.color
import com.jakewharton.rxbinding.widget.text
import com.jakewharton.rxbinding.widget.textChanges
import rx.functions.Action1

/**
 * Created by benny on 12/15/15.
 */

fun Drawable.level(): Action1<Int> = Action1<Int> { t -> setLevel(t) }
fun Drawable.level(path: String, mode: BindingMode, converter: Any? = EmptyOneWayConverter<Any>()) : PropertyBinding = when(mode) {
    BindingMode.OneWay -> BindingAssembler.oneWayPropertyBinding<Int, Any>(path, level(), converter as? OneWayConverter<Int>)
    BindingMode.OneWayToSource -> throw UnsupportedOperationException("OneWayToSource not allowed for drawable level")
    BindingMode.TwoWay -> throw UnsupportedOperationException("TwoWay not allowed for drawable level")
}
fun Drawable.level(paths: List<String>, converter: MultipleConverter<Int>) : PropertyBinding = BindingAssembler.multiplePropertyBinding(paths, level(), converter)

fun View.click(path: String) : PropertyBinding {
    return BindingAssembler.commandBinding(path, clicks(), enabled())
}

fun View.enabled(path: String, mode: BindingMode, converter: Any? = EmptyOneWayConverter<Any>()) : PropertyBinding = when(mode) {
    BindingMode.OneWay -> BindingAssembler.oneWayPropertyBinding<Boolean, Any>(path, enabled(), converter as? OneWayConverter<Boolean>)
    BindingMode.OneWayToSource -> throw UnsupportedOperationException("OneWayToSource not allowed for enabled")
    BindingMode.TwoWay -> throw UnsupportedOperationException("TwoWay not allowed for enabled")
}
fun View.enabled(paths: List<String>, converter: MultipleConverter<Boolean>) : PropertyBinding = BindingAssembler.multiplePropertyBinding(paths, enabled(), converter)

fun View.visibility(path: String, mode: BindingMode, converter: Any? = EmptyOneWayConverter<Any>()) : PropertyBinding = when(mode) {
    BindingMode.OneWay -> BindingAssembler.oneWayPropertyBinding<Boolean, Any>(path, visibility(), converter as? OneWayConverter<Boolean>)
    BindingMode.OneWayToSource -> throw UnsupportedOperationException("OneWayToSource not allowed for visibility")
    BindingMode.TwoWay -> throw UnsupportedOperationException("TwoWay not allowed for visibility")
}
fun View.visibility(paths: List<String>, converter: MultipleConverter<Boolean>) : PropertyBinding = BindingAssembler.multiplePropertyBinding(paths, visibility(), converter)

fun TextView.texColor(path: String, mode: BindingMode, converter: Any? = EmptyOneWayConverter<Any>()) : PropertyBinding = when(mode) {
    BindingMode.OneWay -> BindingAssembler.oneWayPropertyBinding<Int, Any>(path, color(), converter as? OneWayConverter<Int>)
    BindingMode.OneWayToSource -> throw UnsupportedOperationException("OneWayToSource not allowed for text color")
    BindingMode.TwoWay -> throw UnsupportedOperationException("TwoWay not allowed for text color")
}
fun TextView.texColor(paths: List<String>, converter: MultipleConverter<Int>) : PropertyBinding = BindingAssembler.multiplePropertyBinding(paths, color(), converter)

fun TextView.text(path: String, mode: BindingMode, converter: Any? = EmptyOneWayConverter<Any>()) : PropertyBinding = when(mode) {
    BindingMode.OneWay -> BindingAssembler.oneWayPropertyBinding<CharSequence, Any>(path, text(), converter as? OneWayConverter<CharSequence>)
    BindingMode.OneWayToSource -> BindingAssembler.oneWayPropertyBinding<String, Any>(path, textChanges().map { it.toString() }.skip(1), converter as? OneWayConverter<Any>)
    BindingMode.TwoWay -> BindingAssembler.twoWayPropertyBinding(path, textChanges().map { it.toString() }.skip(1), text(), converter as? TwoWayConverter<String, String>)
}
fun TextView.text(paths: List<String>, converter: MultipleConverter<CharSequence>) : PropertyBinding = BindingAssembler.multiplePropertyBinding(paths, text(), converter)
