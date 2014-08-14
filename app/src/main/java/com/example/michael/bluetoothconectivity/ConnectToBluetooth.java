package com.example.michael.bluetoothconectivity;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.io.IOException;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ConnectToBluetooth extends Activity {

    private Button OnButton,Visible,list,connect;
    private ImageButton UWP, GoldBar,Rainbow,Fourth;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    private BluetoothDevice mmDevice;
    private BluetoothSocket mmSocket;
    private OutputStream mmOutputStream;
    private ListView lv;
    private GridLayout gl;
    private boolean attemptedConection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attemptedConection = false;
        setContentView(R.layout.activity_connect_to_bluetooth);
        OnButton = (Button)findViewById(R.id.button1);
        UWP = (ImageButton)findViewById(R.id.uwpButton);
        GoldBar = (ImageButton)findViewById(R.id.rainbowButton);
        Rainbow = (ImageButton)findViewById(R.id.goldBarButton);
        Fourth = (ImageButton)findViewById(R.id.fourthOfJulyButton);
        // Visible = (Button)findViewById(R.id.button2);
       // list = (Button)findViewById(R.id.button3);
        connect = (Button)findViewById(R.id.button5);
        //gl = (GridLayout)findViewById(R.id.grid);
        //lv = (ListView)findViewById(R.id.listView1)
        BA = BluetoothAdapter.getDefaultAdapter();

    }

    public void onButton(View view){
        if (!BA.isEnabled()){
            OnButton.setText(R.string.off);
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(),"Turned on",Toast.LENGTH_LONG).show();
        }
        else {
            BA.disable();
            OnButton.setText(R.string.on);
            Toast.makeText(getApplicationContext(), "Turned Off", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
    }
    //Just a test
    /*
    public void list(View view){
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();
        for (BluetoothDevice bt : pairedDevices) {
            String info = bt.getName()+"\n"+bt.getType()+"\n"+bt.getAddress()+"\n"+bt.getUuids()
                          +"\n"+bt.getBluetoothClass()+"\n"+bt.getBondState();
            list.add(info);

        }

        Toast.makeText(getApplicationContext(),"Showing Paired Devices",Toast.LENGTH_LONG).show();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
    }
    */

    public void connect(View view) {
        pairedDevices = BA.getBondedDevices();
        Toast.makeText(getApplicationContext(), "Connected to Emblem", Toast.LENGTH_LONG).show();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName().equals("Adafruit EZ-Link 1573")) {
                    mmDevice = device;
                    break;
                }
            }
        }
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        try {
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Bluetooth Not Available", Toast.LENGTH_LONG).show();
        }
        try {
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Emblem is out of range", Toast.LENGTH_LONG).show();
        }
        try {
            mmSocket.connect();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Get Closer and try again", Toast.LENGTH_LONG).show();
        }
        try {
            mmOutputStream = mmSocket.getOutputStream();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Really your too far away", Toast.LENGTH_LONG).show();
        }
        if (mmSocket.isConnected()) {
            //gl.setVisibility(GridLayout.VISIBLE);
            Toast.makeText(getApplicationContext(), "Connected to Emblem", Toast.LENGTH_LONG).show();
        }
    }

    public void uwp(View view){
        if (attemptedConection == false)
        {
            connect(view);
        }
        String msg = "A";
        try{
            mmOutputStream.write(msg.getBytes());
        }
        catch( IOException e ){
            Toast.makeText(getApplicationContext(), "Couldn't send", Toast.LENGTH_LONG).show();
        }
    }

    public void fourthOfJuly(View view){
        if (attemptedConection == false)
        {
            connect(view);
        }
        String msg = "B";
        try{
            mmOutputStream.write(msg.getBytes());
        }
        catch( IOException e ){
            Toast.makeText(getApplicationContext(), "Couldn't send", Toast.LENGTH_LONG).show();
        }
    }

    public void goldBar(View view){
        if (attemptedConection == false)
        {
            connect(view);
        }
        String msg = "C";
        try{
            mmOutputStream.write(msg.getBytes());
        }
        catch( IOException e ){
            Toast.makeText(getApplicationContext(), "Couldn't send", Toast.LENGTH_LONG).show();
        }
    }

    public void rainbow(View view){
        if (attemptedConection == false)
        {
            connect(view);
        }
        String msg = "D";
        try{
            mmOutputStream.write(msg.getBytes());
        }
        catch( IOException e ){
            Toast.makeText(getApplicationContext(), "Couldn't send", Toast.LENGTH_LONG).show();
        }
    }



    /*

    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible,0);
    }

    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.connect_to_bluetooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
