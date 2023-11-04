package com.example.sensorproximidadruleta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    ConstraintLayout cl;//El ConstraintLayout que es el contenedor
    TextView texto;//El TextView que se utilizara oara mostrar un mensaje por pantalla 3 usages
    SensorManager sm;//Objeto que se utilizara para gestionar los sensores del dispositivos
    Sensor sensor; //Obtiene el sensor de proximidad
    ImageView ruleta;//objeto ImageView que representa la ruleta en la interfaz de usuario 2 usages
    Random random;//Objeto para generar angulos aleatoreos para la rotacion de la ruleta 2 usages
    int angulo;
    boolean restart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ruleta = findViewById(R.id.ruleta);
        cl = findViewById(R.id.Caja);
        texto = findViewById(R.id.txt_texto);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        random = new Random();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String texto1 = String.valueOf(event.values[0]);
        texto.setText(texto1);
        float valor = Float.parseFloat(texto1);
        if (valor == 0){
            angulo = random.nextInt(3600)+360;
            RotateAnimation rotate = new RotateAnimation(0, angulo,
            RotateAnimation.RELATIVE_TO_SELF,0.5f,
            RotateAnimation.RELATIVE_TO_PARENT,0.5f);
        rotate.getFillAfter();
        rotate.setDuration(3600);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        ruleta.startAnimation(rotate);
        restart = false;

        } else{
            texto.setText("Pasa tu mano para jugar");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}