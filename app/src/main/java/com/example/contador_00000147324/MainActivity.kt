package com.example.contador_00000147324

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.contador_00000147324.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var cuenta: Int = 0
    var cosa: String? = "Cosa"
    lateinit var count: TextView
    lateinit var queEs: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_suma: ImageButton = findViewById(R.id.btn_add)
        val btn_resta: ImageButton = findViewById(R.id.btn_substract)
        val btn_borrar: ImageButton = findViewById(R.id.btn_delete)
        count = findViewById(R.id.count)
        queEs = findViewById(R.id.el_what)

        btn_suma.setOnClickListener{
            cuenta++
            count.setText("$cuenta")
        }

        btn_resta.setOnClickListener{
            cuenta--
            count.setText("$cuenta")
        }

        btn_borrar.setOnClickListener{
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Borrar",
                        DialogInterface.OnClickListener { dialog, id ->
                            cuenta=0
                            count.setText("$cuenta")
                        })
                    setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                }
                builder?.setMessage("Si")
                    .setTitle("Reiniciar contador?")
                builder.create()
            }
            alertDialog?.show()
        }


    }
    override fun onPause() {
        super.onPause()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        cosa = queEs.text.toString()

        editor.putInt("contador",cuenta)
        editor.putString("cosa",cosa)
        editor.commit()

    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        cuenta = sharedPref.getInt("contador",0)
        cosa = sharedPref.getString("cosa","cosa")
        count.setText("$cuenta")
        queEs.setText("$cosa")

    }


}