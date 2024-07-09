package com.example.twitterintegration.utils.common_extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.theming.error_sheet.ErrorMessageDialog

fun Context.copyText(txt:String?){
    txt ?: return
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", txt)
    clipboard?.setPrimaryClip(clip)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        showToast("Text copied to clipboard")
    }

}
fun Context.showToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun FragmentActivity.showErrorDialog(buttonName:String? = null, msg:String, listener: (() -> Unit)?){
    val dialog = ErrorMessageDialog.newInstance(buttonName, msg, listener)
    dialog.show(this.supportFragmentManager, "error_dialog")
}

fun View.show(show:Boolean){
    visibility = if(show) View.VISIBLE else View.GONE
}