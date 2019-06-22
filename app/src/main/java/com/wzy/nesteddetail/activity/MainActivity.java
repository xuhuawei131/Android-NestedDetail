package com.wzy.nesteddetail.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wzy.nesteddetail.R;
import com.wzy.nesteddetail.adapter.RvAdapter;
import com.wzy.nesteddetail.model.InfoBean;
import com.wzy.nesteddetail.view.NestedScrollingDetailContainer;
import com.wzy.nesteddetail.view.NestedScrollingWebView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView vToolBar;
    private NestedScrollingDetailContainer nestedContainer;

    private NestedScrollingWebView webContainer;
    private RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main );
        initView();
    }
   

    private void initView() {
        vToolBar=findViewById(R.id.v_tool_bar);
        nestedContainer=findViewById(R.id.nested_container);
        webContainer=findViewById(R.id.web_container);
        rvList=findViewById(R.id.rv_list);
        initWebView();
        initRecyclerView();
        initToolBarView();
    }

    private void initToolBarView() {
         vToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nestedContainer.scrollToTarget(rvList);
            }
        });
    }

    private void initWebView() {
         webContainer.getSettings().setJavaScriptEnabled(true);
         webContainer.setWebViewClient(new WebViewClient());
         webContainer.setWebChromeClient(new WebChromeClient());
         webContainer.loadUrl("https://github.com/wangzhengyi/Android-NestedDetail");
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
         rvList.setLayoutManager(layoutManager);
        List<InfoBean> data = getCommentData();
        RvAdapter rvAdapter = new RvAdapter(this, data);
         rvList.setAdapter(rvAdapter);
    }

    private List<InfoBean> getCommentData() {
        List<InfoBean> commentList = new ArrayList<>();
        InfoBean titleBean = new InfoBean();
        titleBean.type = InfoBean.TYPE_TITLE;
        titleBean.title = "评论列表";
        commentList.add(titleBean);
        for (int i = 0; i < 40; i++) {
            InfoBean contentBean = new InfoBean();
            contentBean.type = InfoBean.TYPE_ITEM;
            contentBean.title = "评论标题" + i;
            contentBean.content = "评论内容" + i;
            commentList.add(contentBean);
        }
        return commentList;
    }
}
