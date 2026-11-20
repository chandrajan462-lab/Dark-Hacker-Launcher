package com.darkhacker.launcher

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val green = Color.rgb(57, 255, 20)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        val base = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 18, 24, 18)
            setBackgroundColor(Color.BLACK)
        }
        val title = TextView(this).apply {
            text = "☠  DARK HACKER  ☠\nSYSTEM ONLINE"
            textSize = 26f
            gravity = Gravity.CENTER
            setTextColor(green)
            setPadding(8, 8, 8, 18)
        }
        base.addView(title)
        val row1 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER }
        val row2 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER }
        fun addButton(parent: LinearLayout, label: String, action: () -> Unit) {
            val b = Button(this).apply {
                text = label
                setTextColor(green)
                setBackgroundColor(Color.rgb(15, 30, 15))
                textSize = 16f
                setOnClickListener { action() }
            }
            parent.addView(b, LinearLayout.LayoutParams(0, 76, 1f).apply { setMargins(8, 6, 8, 6) })
        }
        addButton(row1, "MUSIC") { launch(Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_MUSIC)) }
        addButton(row1, "USB PLAYER") { launch(Intent(Intent.ACTION_GET_CONTENT).setType("audio/*")) }
        addButton(row1, "BLUETOOTH") { launch(Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS)) }
        addButton(row2, "RADIO") { Toast.makeText(this, "Open your stereo's built-in Radio app", Toast.LENGTH_LONG).show() }
        addButton(row2, "SETTINGS") { launch(Intent(android.provider.Settings.ACTION_SETTINGS)) }
        addButton(row2, "REBOOT UI") { recreate() }
        base.addView(row1); base.addView(row2)
        setContentView(base)
    }
    private fun launch(intent: Intent) {
        try { startActivity(intent) }
        catch (_: Exception) { Toast.makeText(this, "This function is not available on this stereo.", Toast.LENGTH_LONG).show() }
    }
}
