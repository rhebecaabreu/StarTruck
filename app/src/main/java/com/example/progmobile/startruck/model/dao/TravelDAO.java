package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.bean.Travel;

public class TravelDAO {

    private static final String TABLE_NAME = "travel";

    private static final String COLUMN_ID = "idTravel";
    private static final String COLUMN_CIDADEO = "cidadeOrigem";
    private static final String COLUMN_CIDADED = "cidadeDestino";
    private static final String COLUMN_DATASAIDA= "dataSaida";
    private static final String COLUMN_DATAPCHEGADA = "dataPrevistaChegada" ;
    private static final String COLUMN_DATACHEGADA= "dataChegada";
    private static final String COLUMN_CAMINHAO = "caminhao";
    private static final String COLUMN_MOTORISTA = "motorista";
    private static final String COLUMN_ID_USER = "userId" ;


    SQLiteDatabase db, db2;

    public static final String TABLE_CREATE_TRAVEL =
            "CREATE TABLE travel ("+
                    "idTravel integer not null, " +
                    "cidadeOrigem text not null, "+
                    "cidadeDestino text not null, "+
                    "dataSaida text not null, "+
                    "dataPrevistaChegada text not null, "+
                    "dataChegada text, "+
                    "caminhao text not null, " +
                    "motorista text not null, " +
                    "observacoes text ,"+
                    "userId integer not null,"+
                    "FOREIGN KEY (caminhao) REFERENCES vehicle(idVehicle) ON UPDATE CASCADE ON DELETE NO ACTION, "+
                    "FOREIGN KEY (motorista) REFERENCES driver(idDriver)  ON UPDATE CASCADE ON DELETE NO ACTION, "+
                    "FOREIGN KEY (userId) REFERENCES user(idUser) ON UPDATE NO ACTION ON DELETE CASCADE, "+
                    "PRIMARY KEY(idDriver))";

    public static final String TABLE_DROP_TRAVEL = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static TravelDAO instance;

    private static TravelDAO getInstance(Context context){
        if(instance == null)
            instance = new TravelDAO(context);
        return instance;
    }


    public TravelDAO(Context context){
        BDHelper bdHelper = BDHelper.getInstance(context);
        db = bdHelper.getWritableDatabase();
        db2 = bdHelper.getReadableDatabase();
    }

    public void insert(Travel t){
        ContentValues values = new ContentValues();

        values.put(COLUMN_CIDADEO, t.getCityOrigem());
        values.put(COLUMN_CIDADED, t.getCityDestino());
        values.put(COLUMN_DATACHEGADA, t.getDataChegada());
        values.put(COLUMN_DATAPCHEGADA, t.getDatePrevista());
        values.put(COLUMN_DATASAIDA, t.getDateSaída());
        values.put(COLUMN_ID_USER, t.getIdUser());
        values.put(COLUMN_CAMINHAO, t.getPlacaCaminhao() );
        values.put(COLUMN_MOTORISTA, t.getMotorista());
        values.put(COLUMN_ID, t.getIdTravel());


        db.insert(TABLE_NAME, null, values);
    }


}
