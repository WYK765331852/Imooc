package com.example.lenovo.imooc;

import java.util.List;

/**
 * Created by lenovo on 2017/10/21.
 */

//http://www.imooc.com/api/teacher?type=4&num=30
//    "status": 1,
//    "data":[
//          { "id": 1,
//            "name": "Tony老师聊shell——环境变量配置文件",
//            "picSmall": "http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg",
//            "picBig": "http://img.mukewang.com/55237dcc0001128c06000338.jpg",
//            "description": "为你带来shell中的环境变量配置文件",
//            "learner": 12312
//     }
// ]
//    "msg": "成功"
public class ImoocBean {
    public int status;
    public String msg;
    public List<DataBean> data;

    //      public int getStatus(){
//        return status;
//    }
//    public String getMsg(){
//        return msg;
//    }
//    public List<DataBean> getData(){
//        return data;
//    }
    public static class DataBean {
        public int id;
        public String name;
        public String picSmall;
        public String picBig;
        public String description;
        public int learner;
    }

}
