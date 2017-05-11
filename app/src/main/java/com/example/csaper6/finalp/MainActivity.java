package com.example.csaper6.finalp;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.peak.salut.Callbacks.SalutCallback;
import com.peak.salut.Callbacks.SalutDataCallback;
import com.peak.salut.Salut;
import com.peak.salut.SalutDataReceiver;
import com.peak.salut.SalutServiceData;

import java.security.KeyPair;

import javax.crypto.SealedObject;


public class MainActivity extends AppCompatActivity implements SalutDataCallback{

    private TextView one, two;
    private EditText three;
    private Button button;

    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (EditText) findViewById(R.id.three);
        button = (Button) findViewById(R.id.button);

        SalutDataReceiver dataReceiver = new SalutDataReceiver(MainActivity.this, MainActivity.this);
        SalutServiceData serviceData = new SalutServiceData("sas", 50489, "hello");

        Salut network = new Salut(dataReceiver, serviceData, new SalutCallback() {
            @Override
            public void call() {
                Log.e(TAG, "Sorry, but this device does not support WiFi Direct.");
            }
        });



        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if(adapter != null){
            Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rsa hello = new rsa();
                KeyPair key = hello.MakeKeys();
                SealedObject message = hello.enCrypt(key, three.getText().toString());
                String x = hello.decipher(message, key);
                two.setText(x);
                one.setText(key.getPublic().toString());





            }
        });
    }

//    private void salut() {
//        setContentView(R.layout.activity_salut_try);
//
//        SalutDataReceiver dataReceiver = new SalutDataReceiver(MainActivity.this, MainActivity.this);
//        SalutServiceData serviceData = new SalutServiceData("sas", 50489, "hello");
//
//        Salut network = new Salut(dataReceiver, serviceData, new SalutCallback() {
//            @Override
//            public void call() {
//                Log.e(TAG, "Sorry, but this device does not support WiFi Direct.");
//            }
//        });
//
//
//    }


    @Override
    public void onDataReceived(Object o) {

    }
}



















//region Description
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                KeyPairGenerator kpg = null;
//                try {
//                    kpg = KeyPairGenerator.getInstance("RSA");
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
//                kpg.initialize(1024);
//                KeyPair kp = kpg.genKeyPair();
//                Key publicKey = kp.getPublic();
//                Key privateKey = kp.getPrivate();
//
//                KeyFactory fact = null;
//                try {
//                    fact = KeyFactory.getInstance("RSA");
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
//
//                RSAPublicKeySpec pub = null;
//                try {
//                    pub = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
//                } catch (InvalidKeySpecException e) {
//                    e.printStackTrace();
//                }
//
//                RSAPrivateKeySpec priv = null;
//                try {
//                    priv = fact.getKeySpec(privateKey, RSAPrivateKeySpec.class);
//                } catch (InvalidKeySpecException e) {
//                    e.printStackTrace();
//                }
//
//
//                // Get an instance of the Cipher for RSA encryption/decryption
//                Cipher c = null;
//                try {
//                    c = Cipher.getInstance("RSA");
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                } catch (NoSuchPaddingException e) {
//                    e.printStackTrace();
//                }
//// Initiate the Cipher, telling it that it is going to Encrypt, giving it the public key
//                try {
//                    c.init(Cipher.ENCRYPT_MODE, publicKey);
//                } catch (InvalidKeyException e) {
//                    e.printStackTrace();
//                }
//
//                String myMessage = three.getText().toString();
//                SealedObject myEncryptedMessage= null;
//                try {
//                    myEncryptedMessage = new SealedObject( myMessage, c);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (IllegalBlockSizeException e) {
//                    e.printStackTrace();
//                }
//
//                Cipher dec = null;
//                try {
//                    dec = Cipher.getInstance("RSA");
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                } catch (NoSuchPaddingException e) {
//                    e.printStackTrace();
//                }
//// Initiate the Cipher, telling it that it is going to Decrypt, giving it the private key
//                try {
//                    dec.init(Cipher.DECRYPT_MODE, privateKey);
//                } catch (InvalidKeyException e) {
//                    e.printStackTrace();
//                }
//                String message = null;
//                try {
//                    message = (String) myEncryptedMessage.getObject(dec);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IllegalBlockSizeException e) {
//                    e.printStackTrace();
//                } catch (BadPaddingException e) {
//                    e.printStackTrace();
//                }
//
//
//
//                one.setText(priv.getPrivateExponent().toString());
//                two.setText(message);
//
//
//            }
//        });
//
//endregion

