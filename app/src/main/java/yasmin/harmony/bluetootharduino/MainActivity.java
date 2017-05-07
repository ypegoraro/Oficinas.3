package yasmin.harmony.bluetootharduino;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /* Definição dos objetos que serão usados na Activity Principal
        statusMessage mostrará mensagens de status sobre a conexão
        counterMessage mostrará o valor do contador como recebido do Arduino
        connect é a thread de gerenciamento da conexão Bluetooth
     */
    static TextView statusMessage;
    static TextView counterMessage;
    ConnectionThread connect;
    Button btn;
    static MediaPlayer mp;
    static SoundPool sp;
    static private int soundIDp1, soundIDp2, soundIDp3, soundIDp4, soundIDp5, soundIDp6, soundIDp7, soundIDp8, soundIDp9, soundIDp10,
    soundIDp11, soundIDp12, soundIDp13, soundIDp14, soundIDp15, soundIDp16, soundIDp17, soundIDp18, soundIDp19, soundIDp20, soundIDp21,
    soundIDp22, soundIDp23, soundIDp24, soundIDp25, soundIDp26, soundIDp27, soundIDp28, soundIDp29, soundIDp30, soundIDp31, soundIDp32,
    soundIDp33, soundIDp34, soundIDp35, soundIDp36, soundIDp37, soundIDp38, soundIDp39, soundIDp40, soundIDp41, soundIDp42, soundIDp43,
    soundIDp44, soundIDp45, soundIDp46, soundIDp47, soundIDp48, soundIDp49, soundIDp50, soundIDp51, soundIDp52, soundIDp53, soundIDp54,
    soundIDp55, soundIDp56, soundIDp57, soundIDp58, soundIDp59, soundIDp60;
    static boolean plays = false, loaded = false;
    static float actVolume, maxVolume, volume;
    AudioManager audioManager;
    static int counter;
    static int mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Link entre os elementos da interface gráfica e suas
            representações em Java.
         */
        statusMessage = (TextView) findViewById(R.id.statusMessage);
        counterMessage = (TextView) findViewById(R.id.counterMessage);
        btn = (Button) findViewById(R.id.button);
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        // btn.setOnClickListener(this);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        counter = 0;

        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool sp, int sampleId, int status) {
                loaded = true;
            }
        });
        soundIDp1 = sp.load(this, R.raw.piano1, 1);
        soundIDp2 = sp.load(this, R.raw.piano2, 1);
        soundIDp3 = sp.load(this, R.raw.piano3, 1);
        soundIDp4 = sp.load(this, R.raw.piano4, 1);
        soundIDp5 = sp.load(this, R.raw.piano5, 1);
        soundIDp5 = sp.load(this, R.raw.piano5, 1);
        soundIDp6 = sp.load(this, R.raw.piano6, 1);
        soundIDp7 = sp.load(this, R.raw.piano7, 1);
        soundIDp8 = sp.load(this, R.raw.piano8, 1);
        soundIDp9 = sp.load(this, R.raw.piano9, 1);
        soundIDp10 = sp.load(this, R.raw.piano10, 1);
        soundIDp11 = sp.load(this, R.raw.piano11, 1);
        soundIDp12 = sp.load(this, R.raw.piano12, 1);
        soundIDp13 = sp.load(this, R.raw.piano13, 1);
        soundIDp14 = sp.load(this, R.raw.piano14, 1);
        soundIDp15 = sp.load(this, R.raw.piano15, 1);
        soundIDp16 = sp.load(this, R.raw.piano16, 1);
        soundIDp17 = sp.load(this, R.raw.piano17, 1);
        soundIDp18 = sp.load(this, R.raw.piano18, 1);
        soundIDp19 = sp.load(this, R.raw.piano19, 1);
        soundIDp20 = sp.load(this, R.raw.piano20, 1);
        soundIDp21 = sp.load(this, R.raw.piano21, 1);
        soundIDp22 = sp.load(this, R.raw.piano22, 1);
        soundIDp23 = sp.load(this, R.raw.piano23, 1);
        soundIDp24 = sp.load(this, R.raw.piano24, 1);
        soundIDp25 = sp.load(this, R.raw.piano25, 1);
        soundIDp26 = sp.load(this, R.raw.piano26, 1);
        soundIDp27 = sp.load(this, R.raw.piano27, 1);
        soundIDp28 = sp.load(this, R.raw.piano28, 1);
        soundIDp29 = sp.load(this, R.raw.piano29, 1);
        soundIDp30 = sp.load(this, R.raw.piano30, 1);
        soundIDp31 = sp.load(this, R.raw.piano31, 1);
        soundIDp32 = sp.load(this, R.raw.piano32, 1);
        soundIDp33 = sp.load(this, R.raw.piano33, 1);
        soundIDp34 = sp.load(this, R.raw.piano34, 1);
        soundIDp35 = sp.load(this, R.raw.piano35, 1);
        soundIDp36 = sp.load(this, R.raw.piano36, 1);
        soundIDp37 = sp.load(this, R.raw.piano37, 1);
        soundIDp38 = sp.load(this, R.raw.piano38, 1);
        soundIDp39 = sp.load(this, R.raw.piano39, 1);
        soundIDp40 = sp.load(this, R.raw.piano40, 1);
        soundIDp41 = sp.load(this, R.raw.piano41, 1);
        soundIDp42 = sp.load(this, R.raw.piano42, 1);
        soundIDp43 = sp.load(this, R.raw.piano43, 1);
        soundIDp44 = sp.load(this, R.raw.piano44, 1);
        soundIDp45 = sp.load(this, R.raw.piano45, 1);
        soundIDp46 = sp.load(this, R.raw.piano46, 1);
        soundIDp47 = sp.load(this, R.raw.piano47, 1);
        soundIDp48 = sp.load(this, R.raw.piano48, 1);
        soundIDp49 = sp.load(this, R.raw.piano49, 1);
        soundIDp50 = sp.load(this, R.raw.piano50, 1);
        soundIDp51 = sp.load(this, R.raw.piano51, 1);
        soundIDp52 = sp.load(this, R.raw.piano52, 1);
        soundIDp53 = sp.load(this, R.raw.piano53, 1);
        soundIDp54 = sp.load(this, R.raw.piano54, 1);
        soundIDp55 = sp.load(this, R.raw.piano55, 1);
        soundIDp56 = sp.load(this, R.raw.piano56, 1);
        soundIDp57 = sp.load(this, R.raw.piano57, 1);
        soundIDp58 = sp.load(this, R.raw.piano58, 1);
        soundIDp59 = sp.load(this, R.raw.piano59, 1);
        soundIDp60 = sp.load(this, R.raw.piano60, 1);



        /* Teste rápido. O hardware Bluetooth do dispositivo Android
            está funcionando ou está bugado de forma misteriosa?
            Será que existe, pelo menos? Provavelmente existe.
         */
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            statusMessage.setText("Que pena! Hardware Bluetooth não está funcionando :(");
        } else {
            statusMessage.setText("Ótimo! Hardware Bluetooth está funcionando :)");
        }

        /* A chamada do seguinte método liga o Bluetooth no dispositivo Android
            sem pedido de autorização do usuário. É altamente não recomendado no
            Android Developers, mas, para simplificar este app, que é um demo,
            faremos isso. Na prática, em um app que vai ser usado por outras
            pessoas, não faça isso.
         */
        btAdapter.enable();

        /* Definição da thread de conexão como cliente.
            Aqui, você deve incluir o endereço MAC do seu módulo Bluetooth.
            O app iniciará e vai automaticamente buscar por esse endereço.
            Caso não encontre, dirá que houve um erro de conexão.
         */
        connect = new ConnectionThread("98:D3:31:90:60:7A");
        connect.start();

        /* Um descanso rápido, para evitar bugs esquisitos.
         */
        try {
            Thread.sleep(1000);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

   /* @Override
    public void onClick(View v) {
        restartCounter(v);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_bluetooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            /* Esse método é invocado na Activity principal
                sempre que a thread de conexão Bluetooth recebe
                uma mensagem.
             */
                Bundle bundle = msg.getData();
                byte[] data = bundle.getByteArray("data");
                String dataString = new String(data);

            /* Aqui ocorre a decisão de ação, baseada na string
                recebida. Caso a string corresponda à uma das
                mensagens de status de conexão (iniciadas com --),
                atualizamos o status da conexão conforme o código.
             */

                if (dataString.equals("---N"))
                    statusMessage.setText("Ocorreu um erro durante a conexão D:");
                if (dataString.equals("---S"))
                    statusMessage.setText("Conectado :D");
                statusMessage.setText("oi " + dataString + "tchau");
                if (dataString.equals("t1")) {
                    if (loaded && !plays) {
                        sp.play(soundIDp1, volume, volume, 1, 0, 1f);
                        counter = counter++;
                        plays = true;
                    }
                    plays = false;
                }
            if (dataString.equals("t1")) {
                if (loaded && !plays) {
                    sp.play(soundIDp1, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t2")) {
                if (loaded && !plays) {
                    sp.play(soundIDp2, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t3")) {
                if (loaded && !plays) {
                    sp.play(soundIDp3, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t4")) {
                if (loaded && !plays) {
                    sp.play(soundIDp4, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t5")) {
                if (loaded && !plays) {
                    sp.play(soundIDp5, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t6")) {
                if (loaded && !plays) {
                    sp.play(soundIDp6, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t7")) {
                if (loaded && !plays) {
                    sp.play(soundIDp7, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t8")) {
                if (loaded && !plays) {
                    sp.play(soundIDp8, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t9")) {
                if (loaded && !plays) {
                    sp.play(soundIDp9, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t10")) {
                if (loaded && !plays) {
                    sp.play(soundIDp10, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t11")) {
                if (loaded && !plays) {
                    sp.play(soundIDp11, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t12")) {
                if (loaded && !plays) {
                    sp.play(soundIDp12, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t13")) {
                if (loaded && !plays) {
                    sp.play(soundIDp13, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t14")) {
                if (loaded && !plays) {
                    sp.play(soundIDp14, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t15")) {
                if (loaded && !plays) {
                    sp.play(soundIDp15, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t16")) {
                if (loaded && !plays) {
                    sp.play(soundIDp16, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t17")) {
                if (loaded && !plays) {
                    sp.play(soundIDp17, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t18")) {
                if (loaded && !plays) {
                    sp.play(soundIDp18, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t19")) {
                if (loaded && !plays) {
                    sp.play(soundIDp19, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t20")) {
                if (loaded && !plays) {
                    sp.play(soundIDp20, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t21")) {
                if (loaded && !plays) {
                    sp.play(soundIDp21, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t22")) {
                if (loaded && !plays) {
                    sp.play(soundIDp22, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t23")) {
                if (loaded && !plays) {
                    sp.play(soundIDp23, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t24")) {
                if (loaded && !plays) {
                    sp.play(soundIDp24, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t25")) {
                if (loaded && !plays) {
                    sp.play(soundIDp25, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t26")) {
                if (loaded && !plays) {
                    sp.play(soundIDp26, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t27")) {
                if (loaded && !plays) {
                    sp.play(soundIDp27, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t28")) {
                if (loaded && !plays) {
                    sp.play(soundIDp28, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t29")) {
                if (loaded && !plays) {
                    sp.play(soundIDp29, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t30")) {
                if (loaded && !plays) {
                    sp.play(soundIDp30, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t31")) {
                if (loaded && !plays) {
                    sp.play(soundIDp31, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t32")) {
                if (loaded && !plays) {
                    sp.play(soundIDp32, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t33")) {
                if (loaded && !plays) {
                    sp.play(soundIDp33, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t34")) {
                if (loaded && !plays) {
                    sp.play(soundIDp34, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t35")) {
                if (loaded && !plays) {
                    sp.play(soundIDp35, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t36")) {
                if (loaded && !plays) {
                    sp.play(soundIDp36, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t37")) {
                if (loaded && !plays) {
                    sp.play(soundIDp37, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t38")) {
                if (loaded && !plays) {
                    sp.play(soundIDp38, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t39")) {
                if (loaded && !plays) {
                    sp.play(soundIDp1, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t39")) {
                if (loaded && !plays) {
                    sp.play(soundIDp1, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t40")) {
                if (loaded && !plays) {
                    sp.play(soundIDp40, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t41")) {
                if (loaded && !plays) {
                    sp.play(soundIDp41, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t42")) {
                if (loaded && !plays) {
                    sp.play(soundIDp1, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t43")) {
                if (loaded && !plays) {
                    sp.play(soundIDp43, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t44")) {
                if (loaded && !plays) {
                    sp.play(soundIDp44, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t45")) {
                if (loaded && !plays) {
                    sp.play(soundIDp45, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t46")) {
                if (loaded && !plays) {
                    sp.play(soundIDp46, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t47")) {
                if (loaded && !plays) {
                    sp.play(soundIDp47, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t48")) {
                if (loaded && !plays) {
                    sp.play(soundIDp48, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t49")) {
                if (loaded && !plays) {
                    sp.play(soundIDp49, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t50")) {
                if (loaded && !plays) {
                    sp.play(soundIDp50, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t50")) {
                if (loaded && !plays) {
                    sp.play(soundIDp50, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t51")) {
                if (loaded && !plays) {
                    sp.play(soundIDp51, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t52")) {
                if (loaded && !plays) {
                    sp.play(soundIDp52, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }if (dataString.equals("t53")) {
                if (loaded && !plays) {
                    sp.play(soundIDp53, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t54")) {
                if (loaded && !plays) {
                    sp.play(soundIDp54, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t55")) {
                if (loaded && !plays) {
                    sp.play(soundIDp55, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t56")) {
                if (loaded && !plays) {
                    sp.play(soundIDp56, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t57")) {
                if (loaded && !plays) {
                    sp.play(soundIDp57, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t58")) {
                if (loaded && !plays) {
                    sp.play(soundIDp58, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t59")) {
                if (loaded && !plays) {
                    sp.play(soundIDp59, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
            if (dataString.equals("t60")) {
                if (loaded && !plays) {
                    sp.play(soundIDp60, volume, volume, 1, 0, 1f);
                    counter = counter++;
                    plays = true;
                }
                plays = false;
            }
        

        }



  /*  public void tocaPorFavor(int mensagem){
        if(mensagem == 1){
            try {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                mp.reset();
                AssetFileDescriptor afd = null;
                switch (v.getId()) {
                    case R.id.btn:
                        afd = getResources().openRawResourceFd(R.raw.som);
                        break;
                }
                if (afd != null) {
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepareAsync();
                }
            } catch (IOException e) {
                Log.e("", e.getMessage());
            }
        }
    }*/

                /* Se a mensagem não for um código de status,
                    então ela deve ser tratada pelo aplicativo
                    como uma mensagem vinda diretamente do outro
                    lado da conexão. Nesse caso, simplesmente
                    atualizamos o valor contido no TextView do
                    contador.
                 */
            //  counterMessage.setText(dataString);


    };

    /* Esse método é invocado sempre que o usuário clicar na TextView
        que contem o contador. O app Android transmite a string "restart",
        seguido de uma quebra de linha, que é o indicador de fim de mensagem.
     */
    /*public void restartCounter(View view) {

        connect.write("restart\n".getBytes());
    }*/
}


