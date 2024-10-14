package com.example.p2_u1_eval1

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.p2_u1_eval1.modelo.CuentaMesa
import com.example.p2_u1_eval1.modelo.ItemMenu
import com.example.p2_u1_eval1.modelo.ItemMesa
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    //Declaración de views en variables
    private var editTextPastel : EditText? = null
    private var editTextCazuela : EditText? = null
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private var switchPropina : Switch? = null
    private var textViewPastel : TextView? = null
    private var textViewCazuela : TextView? = null
    private var textViewComida : TextView? = null
    private var textViewPropina : TextView? = null
    private var textViewTotal : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Encuentra los views por sus ID
        editTextPastel = findViewById<EditText>(R.id.etPastel)
        editTextCazuela = findViewById<EditText>(R.id.etCazuela)
        switchPropina = findViewById<Switch>(R.id.swPropina)
        textViewPastel = findViewById<TextView>(R.id.tvTotalPastel)
        textViewCazuela = findViewById<TextView>(R.id.tvTotalCazuela)
        textViewComida = findViewById<TextView>(R.id.tvComida)
        textViewPropina = findViewById<TextView>(R.id.tvPropina)
        textViewTotal = findViewById<TextView>(R.id.tvTotal)

        //Establece el evento de cambio de texto de los editText
        val textWatcher:TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                mostrarTotal()
            }

        }
        editTextPastel?.addTextChangedListener(textWatcher)
        editTextCazuela?.addTextChangedListener(textWatcher)

        //Establece el evento al interactuar con el switch
        switchPropina?.setOnCheckedChangeListener { buttonView, isChecked ->
            mostrarTotal()
        }
    }

    //Función que toma los valores del formulario y calcula todos los totales y los muestra
    private fun mostrarTotal(){
        val cuentaMesa = CuentaMesa(1)
        if (switchPropina?.isChecked == true){
            cuentaMesa.aceptaPropina = true
        }else{
            cuentaMesa.aceptaPropina = false
        }
        val pastelMenu = ItemMenu("Pastel de Choclo", "12000")
        val cazuelaMenu = ItemMenu("Cazuela", "10000")
        val pastelMesa = ItemMesa(pastelMenu, editTextPastel?.text.toString().toIntOrNull() ?: 0)
        val cazuelaMesa = ItemMesa(cazuelaMenu, editTextCazuela?.text.toString().toIntOrNull() ?: 0)
        cuentaMesa.agregarItem(pastelMesa.itemMenu, pastelMesa.cantidad)
        cuentaMesa.agregarItem(cazuelaMesa.itemMenu, cazuelaMesa.cantidad)
        val totalPastel = "$" + NumberFormat.getInstance().format(pastelMesa.calcularSubtotal())
        val totalCazuela = "$" + NumberFormat.getInstance().format(cazuelaMesa.calcularSubtotal())
        val totalComida = "$" + NumberFormat.getInstance().format(cuentaMesa.calcularTotalSinPropina())
        var propina = ""
        var totalFinal = ""
        textViewPastel?.setText(totalPastel)
        textViewCazuela?.setText(totalCazuela)
        textViewComida?.setText(totalComida)
        if (cuentaMesa.aceptaPropina) {
            propina = "$" + NumberFormat.getInstance().format(cuentaMesa.calcularPropina())
            totalFinal = "$" + NumberFormat.getInstance().format(cuentaMesa.calcularTotalConPropina())
        }else{
            propina = "$0"
            totalFinal = totalComida
        }
        textViewPropina?.setText(propina)
        textViewTotal?.setText(totalFinal)

    }
}