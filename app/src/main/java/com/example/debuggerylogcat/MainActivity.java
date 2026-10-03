package com.example.debuggerylogcat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText etA, etB; //Cajas de texto
    private Button btnDividir; // Boton para dividir A/B
    private TextView tvResultado; //TextView para el resultado

    private static final String TAG = "CESBA_DEBUG"; //TAG para Logcat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        btnDividir = findViewById(R.id.btnDividir);
        tvResultado = findViewById(R.id.tvResultado); //

        Log.i(TAG, "App iniciada correctamente (onCreate)"); //Vincula TextView de resultado

        btnDividir.setOnClickListener(v ->{
            Log.d(TAG, "Botón presionado");

            String aTxt = etA.getText().toString().trim();
            String bTxt = etB.getText().toString().trim();

            Log.d(TAG, "Entrada A: " + aTxt);
            Log.d(TAG, "Entrada B: " + bTxt);

            if (aTxt.isEmpty() || bTxt.isEmpty()){
                Log.w(TAG, "Campos vacios");
                Toast.makeText(this, "Completa ambos campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try{
                int a = Integer.parseInt(aTxt);
                int b = Integer.parseInt(bTxt);

                Log.i(TAG, "Convertidos: a=" + a + ", b="+b);

                int res= a/b;

                tvResultado.setText("Resultado: " + res);
                Log.i(TAG, "Resultado mostrado: " + res);

            }catch(ArithmeticException e){
                Log.e(TAG, "Division entre 0: " + e.getMessage());
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
            }catch(NumberFormatException e){
                Log.e(TAG, "Formato invalido: " + e.getMessage());
                Toast.makeText(this, "Ingresa numeros validos", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Log.e(TAG, "Error inesperado: " + e.getMessage());
                Toast.makeText(this, "Ocurrio un error.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}