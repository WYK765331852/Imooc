package com.example.lenovo.imooc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by lenovo on 2017/10/21.
 */

public interface ImoocApi {
    @GET("api/teacher")
    Call<ImoocBean> getData(@Query("type") int type, @Query("num") int num);
}
