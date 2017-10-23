package com.example.lenovo.imooc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<ImoocBean.DataBean> dataBeanList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private int TYPE = 4;
    private int NUM = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super可以调用父类中的方法，实现父类的方法，再实现一些自己的功能
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imooc_main_activity);

        loadImoocData(TYPE, NUM);


    }

    public void loadImoocData(int type, int num) {
        ImoocApi api = ApiClient.getRetrofit().create(ImoocApi.class);
        Call<ImoocBean> call = api.getData(type, num);
        call.enqueue(new Callback<ImoocBean>() {
            @Override
            public void onResponse(Call<ImoocBean> call, Response<ImoocBean> response) {
                dataBeanList = response.body().data;
                mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
                mRecyclerView = (RecyclerView) findViewById(R.id.imooc_main_rcv);
                mRecyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, dataBeanList);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
                Log.d("aaaa","onresponse："+response.body().data.size());

            }
            @Override
            public void onFailure(Call<ImoocBean> call, Throwable t) {
                Log.d("aaaa","failure");
            }
        });
    }
}
