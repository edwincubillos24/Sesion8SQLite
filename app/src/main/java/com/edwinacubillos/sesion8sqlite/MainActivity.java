package com.edwinacubillos.sesion8sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eNombre, eTelefono, eCorreo;

    String nombre, correo;
    String telefono;
    Button bInsertar, bActualizar, bBorrar, bBuscar;

    ContentValues dataBD;
    SQLiteDatabase dbContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactosSQLiteHelper contactos = new ContactosSQLiteHelper(this,"ContactosBD", null, 1);
        dbContactos = contactos.getWritableDatabase();

        eNombre = (EditText) findViewById(R.id.eNombre);
        eTelefono = (EditText) findViewById(R.id.eTelefono);
        eCorreo = (EditText) findViewById(R.id.eMail);
        bInsertar = (Button) findViewById(R.id.bInsertar);
        bActualizar = (Button) findViewById(R.id.bActualizar);
        bBorrar = (Button) findViewById(R.id.bBorrar);
        bBuscar = (Button) findViewById(R.id.bBuscar);

        bInsertar.setOnClickListener(this);
        bActualizar.setOnClickListener(this);
        bBorrar.setOnClickListener(this);
        bBuscar.setOnClickListener(this);
 }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        nombre = eNombre.getText().toString();
        correo = eCorreo.getText().toString();
        telefono = eTelefono.getText().toString();

        switch(id){
            case R.id.bInsertar:

                dataBD = new ContentValues();
                dataBD.put("nombre",nombre);
                dataBD.put("telefono",telefono);
                dataBD.put("correo",correo);

                dbContactos.insert("Contactos",null,dataBD);
             //   dbContactos.execSQL("INSERT INTO Contactos VALUES(null, '"+nombre+"', '"+telefono+"','"+correo+"' )");

                break;
            case R.id.bBuscar:

                Cursor c = dbContactos.rawQuery("select * from Contactos where nombre='"+nombre+"'",null);

                if(c.moveToFirst()){
                    eTelefono.setText(c.getString(2));
                    eCorreo.setText(c.getString(3));
                }

                break;

            case R.id.bActualizar:

                dataBD = new ContentValues();
                dataBD.put("telefono",telefono);
                dataBD.put("correo",correo);

               // dbContactos.update("Contactos", dataBD, "nombre='"+nombre+"'", null);
                dbContactos.execSQL("UPDATE Contactos SET telefono='"+telefono+"', correo='"+correo+"' WHERE nombre='"+nombre+"'");

                break;

            case R.id.bBorrar:

              //  dbContactos.delete("Contactos", "nombre='"+nombre+"'",null);
                dbContactos.execSQL("DELETE FROM Contactos WHERE nombre='"+nombre+"'");

                break;
        }
    }
}
