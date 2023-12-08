package com.bomin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog myProgressDlg;
    private Bitmap myBitMap = null;
    Button myButton;
    ImageView myImageView;
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = (Button)findViewById(R.id.button);
        myImageView = (ImageView)findViewById(R.id.imageView);
        myEditText = (EditText)findViewById(R.id.editTextText);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInternetConenction()) {
                    downloadImage(myEditText.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Internet is not connected.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void downloadImage(String urlStr) {
        myProgressDlg = ProgressDialog.show(this, "", "Downloading Image from " + urlStr);
        final String url = urlStr;
        // 뜨레드 처리로 화면 멈춤을 막아줌.
        new Thread() {
            public void run() {
                InputStream in = null;
                Message msg = Message.obtain();
                msg.what = 1;
                try {
                    // http 연결
                    in = openHttpConnection(url);
                    // 다운받은 파일을 비트맵으로 변환시켜 화면에 그려줄 준비를 해줌.
                    myBitMap = BitmapFactory.decodeStream(in);
                    // 비트맵 파일은 메시지 핸들러를 통해 처리되어야 한다. 이를 위해 번들 클래스를
                    // 활용하여 비트맵 파일을 메시지 핸들러에 보낼 수 있도록 한다.
                    // bundle은 패키지 역할(다른 클래스로 보낼 때 쓰이는 미니 클래스 기능) -> 다운로드 받은 파일을 메시지에 추가하기 위해 사용(여기선)
                    if (in != null) {
                        Bundle b = new Bundle();
                        b.putParcelable("bitmap", myBitMap);
                        msg.setData(b);
                    } else {
                        Toast.makeText(getApplicationContext(), "The image is not available at the address you gave.",
                                Toast.LENGTH_LONG).show();
                    }
                    in.close(); //커넥션 연결하면 꼭 닫아야 함
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                // 메시지 핸들러에 이미지 비트맵을 전송(display하는 엑티비티에 보내는 것)
                messageHandler.sendMessage(msg);
            }
        }.start();
    }

    // http 연결과 데이터 송수신을 담당한다.
    //실제로 연결하는 부분
    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;
        try {
            URL url = new URL(urlStr); // 접속 서버의 주소 객체
            URLConnection urlConn = url.openConnection(); //서버에 연결된 객체
            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL"); //사용자가 실수로 잘못 url를 입력했을 때의 에러 처리
            }
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET"); //http의 GET 메소드를 사용한다고 지정(put, update, delete 등 다양한 종류가 있으나 데이터를 읽을 때는 get사용
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) { //상대 서버에서 HTTP_OK를 보내주게 되면 데이터를 수신
                in = httpConn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    // 다운 받은 비트맵 이미지를 이미지 뷰 객체에 그려주어야 한다. 하지만 쓰레드에서는 메인 액티비티의 이미지 뷰 객체를 처리할 수 없으므로 메시지 핸들러를 통해 이미지비트맵 파일을 매인 액티비티에 보내어 처리하도록 한다.
    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myImageView.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap"))); //받은 메시지를 이미지 뷰에 뿌림
            myProgressDlg.dismiss();
        }
    };

    // 인터넷 연결을 connectivityManager 를 사용하여 점검한다.
    // 인터넷이 연결된 상태라면 true 를 아니면 false 를 리턴한다.
    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connect =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        // Check for network connections
        if ( connect.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connect.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connect.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connect.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {
            return true; //연결되면 true
        }else if (
                connect.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                        connect.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {
            return false; //연결 안되면 false
        }
        return false;//이 값도 저 값도 아닌건 false
    }

}
