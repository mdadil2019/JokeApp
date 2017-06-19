package com.environer.jokeapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.environer.jokeapp.R;
import com.environer.myandroidlibrary.JokeShowingActivity;
import com.example.mohammadadil.myapplication.backend.myApi.MyApi;
import com.example.mohammadadil.myapplication.backend.myApi.model.MyBean;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by Mohammad Adil on 15-06-2017.
 */

public class EndpointAsyncTask extends AsyncTask<Pair<Context,String>, Void,String> {
    private static MyApi mJokeApi = null;
    private Context mContext;
    private String mResult;
    private InterstitialAd myInterstititalAd;
    private ProgressBar myProgressBar;

    public EndpointAsyncTask(Context context, ProgressBar progressBar){
        this.mContext = context;
        this.myProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(myProgressBar != null){
            myProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if(mJokeApi ==null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/");
            mJokeApi = builder.build();
        }
        try{
            return mJokeApi.letJoke(new MyBean()).execute().getJoke();
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;

        //Setup the add here
        myInterstititalAd = new InterstitialAd(mContext);
        myInterstititalAd.setAdUnitId(mContext.getString(R.string.adUnit));
        AdRequest ar = new AdRequest
                .Builder()
                .build();
        myInterstititalAd.loadAd(ar);
        myInterstititalAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startJokeDisplayActivity();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                myInterstititalAd.show();
                if(myProgressBar !=null) {
                    myProgressBar.setVisibility(View.GONE);
                }
//                startJokeDisplayActivity();

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if(myProgressBar !=null) {
                    myProgressBar.setVisibility(View.GONE);
                }
                startJokeDisplayActivity();
            }
        });

    }

    private void startJokeDisplayActivity() {
        Intent intent = new Intent(mContext, JokeShowingActivity.class);
        intent.putExtra(JokeShowingActivity.JOKE_INTENT,mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
