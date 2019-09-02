package mx.xx.rootdetectordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import android.widget.Button
import android.widget.TextView

import java.io.File

class MainActivityKotlin : AppCompatActivity() {
    private var et: TextView? = null
    private var checkButton: Button? = null
    // get from build info
    // check if /system/app/Superuser.apk is present
    // ignore
    // try executing commands
    val isRooted: Boolean
        get() {
            try {
                val file = File("/system/app/Superuser.apk")
                if (file.exists()) {
                    return true
                }
            } catch (e1: Exception) {
            }

            return (canExecuteCommand("/system/xbin/which su")
                    || canExecuteCommand("/system/bin/which su") || canExecuteCommand("which su"))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkButton = findViewById(R.id.button) as Button
        et = findViewById(R.id.textView) as TextView

        checkButton!!.setOnClickListener {
            if (isRooted) {
                et!!.text = "RootedKotlin"
            } else {
                et!!.text = "CleanKotlin"
            }
        }

    }

    // executes a command on the system
    private fun canExecuteCommand(command: String): Boolean {
        var executedSuccesfully: Boolean
        try {
            Runtime.getRuntime().exec(command)
            executedSuccesfully = true
        } catch (e: Exception) {
            executedSuccesfully = false
        }

        return executedSuccesfully
    }
}
