package com.example.joanne.practice_layout01;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Joanne on 2018-02-25.
 */

public class ProgressDialogActivity extends Activity {

    String getNum, getRentDate, getUser, getDial, getSms, getProName, getRent, getRentGijun, getDeposit, getRentName, getEail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.progress_dialog );

        CheckTypesTask task = new CheckTypesTask();
        task.execute( );
    }

    //AstncTask<void,void,void>란?
    //void1: doInBackground 파라미터 타입, excute메소드의 인자값
    //void2: doInBackground작업시 진행 단위 타입, onProgressUpdate파라미터타입
    //void3: doInBackground리턴값, onPostExecute파라미터 타입

    private class CheckTypesTask extends AsyncTask<Void,Void,Void> {
        ProgressDialog asyncDialog = new ProgressDialog( ProgressDialogActivity.this );


        // 작업시작, ProgressDialog 객체를 생성하고 시작한다.
        @Override
        protected void onPreExecute() {

            asyncDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
            asyncDialog.setMessage( "주문 처리중입니다." );

            //show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        //진행중, ProgressDialog의 진행정도를 표현해준다.
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0; i<5; i++){
                try {
                    asyncDialog.setProgress( i*30 );
                    Thread.sleep( 500 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //종료, Progressdialog 종료기능을 구현한다.
        @Override
        protected void onPostExecute(Void aVoid) {
            asyncDialog.dismiss();
            super.onPostExecute( aVoid );

            Intent view = new Intent(ProgressDialogActivity.this,OrderConformActivity.class);
            view.setAction( Intent.ACTION_VIEW );
            startActivity( view );
            finish();

        }
    }


}
