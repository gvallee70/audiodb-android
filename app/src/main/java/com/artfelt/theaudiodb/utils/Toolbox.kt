package com.artfelt.theaudiodb.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso

class Toolbox {

    companion object {
        fun showSimpleCustomDialog(
            context: Context,
            message: String,
            positiveText: String?,
            negativetext: String?,
            validate: () -> Unit
        ): AlertDialog {
            val dialog = AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(negativetext, null)
                .setPositiveButton(positiveText) { _, _ ->
                    validate()
                }
            return dialog.show()
        }
    }

}



fun ImageView.setImageURL(url: String) {
    Picasso.get().load(url).into(this)
}
