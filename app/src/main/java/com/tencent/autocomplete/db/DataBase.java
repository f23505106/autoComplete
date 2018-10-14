package com.tencent.autocomplete.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tencent.autocomplete.util.T9Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Database(entities = {CatalogueEntity.class,AbbrEntity.class,DetailEntity.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    private static DataBase INSTANCE;
    private static final String DB_NAME = "yellow_page.db";
    private static String TAG = "fgt";

    public static DataBase getDatabase(final Context context) {
        Log.d(TAG,"get database:"+INSTANCE);
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    Log.d(TAG,"before create database:");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d(TAG, "populating with data...");
                                    insertTestData(INSTANCE,context);
                                }
                            })
                            .build();
                    Log.d(TAG,"after database:");

                }
            }
        }
        return INSTANCE;
    }
    public abstract CatalogueDao catalogueDao();
    public abstract AbbrDao abbrDao();
    public abstract DetailDao detailDao();

    private static void insertTestData(final DataBase db,final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                CatalogueDao cdao = db.catalogueDao();
                AbbrDao adao = db.abbrDao();
                DetailDao ddao = db.detailDao();

                cdao.deleteAll();
                adao.deleteAll();
                ddao.deleteAll();

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(context.getAssets().open("database.cvs")));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Log.d(TAG,line);
                        //1   农业银行    http://www.abchina.com/cn/images/logo_ue2.png   中国农业银行中关村支行 zhong,guo,nong,ye,yin,hang,zhong,guan,cun,zhi,hang  银行  01082607593
                        String[] sl =line.split("\t");
                        Log.d(TAG,"len:"+sl.length);
                        if(sl.length != 7){
                            Log.d(TAG,"line format illegal");
                            continue;
                        }
                        CatalogueEntity cat = cdao.findCatalogue(sl[5]);
                        if(cat == null){
                            Log.d(TAG,"create new catalogue:"+sl[5]);
                            cat = new CatalogueEntity(sl[5]);
                            cat.id = (int)cdao.save(cat);
                        }
                        AbbrEntity abbr = adao.findAbbr(sl[1]);
                        if(abbr == null){
                            Log.d(TAG,"create new abbr:"+sl[1]);
                            abbr = new AbbrEntity(Integer.valueOf(sl[0]),sl[1],sl[2],cat.id);
                            abbr.id = (int)adao.save(abbr);
                        }
                        T9Data t9 = new T9Data(sl[4]);
                        Log.d(TAG,"insert new detail:"+sl[3]+"  "+sl[6]);
                        DetailEntity detail = new DetailEntity(sl[3],t9.t9,t9.t9Index,t9.t9abbr,sl[6],abbr.id);
                        ddao.save(detail);

                        //List<DetailEntity> sr = ddao.findT9("10086");


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }

}
