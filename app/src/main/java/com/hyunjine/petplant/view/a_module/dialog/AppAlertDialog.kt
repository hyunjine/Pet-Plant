package com.hyunjine.petplant.view.a_module.dialog

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.hyunjine.petplant.R


class AppAlertDialog(
    private val context: Context,
    private val title: String? = null,
    private val msg: String?= null
) {
    private lateinit var positiveListener: () -> Unit
    private lateinit var negativeListener: () -> Unit
    private val dialog: AlertDialog.Builder by lazy {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                if (::positiveListener.isInitialized) positiveListener()
            }
            .setNegativeButton(context.getString(R.string.cancel)) { _, _ ->
                if (::negativeListener.isInitialized) negativeListener()
            }.setNeutralButton("") { _, _ ->

            }
    }

    fun show(onClickNegative: () -> Unit = {}, onClickPositive: () -> Unit = {}) {
        this.negativeListener = onClickNegative
        this.positiveListener = onClickPositive
        dialog.show().apply {
            /**
             * Title TextView
             */
            findViewById<TextView>(androidx.appcompat.R.id.alertTitle)?.apply {
                textSize = 10f
                typeface = ResourcesCompat.getFont(context, R.font.dalseo_r)
                setTextColor(context.getColor(R.color.sub_text))
            }
            /**
             * Message TextView
             */
            findViewById<TextView>(android.R.id.message)?.apply {
                textSize = 10f
                typeface = ResourcesCompat.getFont(context, R.font.dalseo_r)
                setTextColor(context.getColor(R.color.sub_text))
            }
            /**
             * Positive Button
             */
            findViewById<TextView>(android.R.id.button1)?.apply {
                textSize = 18f
                typeface = ResourcesCompat.getFont(context, R.font.dalseo_r)
                setTextColor(context.getColor(R.color.red))
            }
            /**
             * Negative Button
             */
            findViewById<TextView>(android.R.id.button2)?.apply {
                textSize = 18f
                typeface = ResourcesCompat.getFont(context, R.font.dalseo_r)
                setTextColor(context.getColor(R.color.main_green))
            }
            /**
             * Neutral Button
             */
            findViewById<TextView>(android.R.id.button3)?.apply {
                textSize = 18f
                typeface = ResourcesCompat.getFont(context, R.font.dalseo_r)
                setTextColor(context.getColor(R.color.sub_text))
            }
        }
    }
}