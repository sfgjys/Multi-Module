package com.zhq.multi_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhq.baselibrary.rxjava.ObserveSerialNumber;
import com.zhq.baselibrary.rxjava.ObserverSchemaManager;
import com.zhq.jpush.DistributeHandleResult;
import com.zhq.jpush.aliastag.AliasTagOperatorHelper;
import com.zhq.jpush.bean.AliasTagsBean;
import com.zhq.jpush.tool.JPUSHTools;

import java.util.LinkedHashSet;
import java.util.Set;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 别名的操作分类
    public static final int ALIAS_ACTION_SET = 0;
    public static final int ALIAS_ACTION_GET = 1;
    public static final int ALIAS_ACTION_DELETE = 2;
    // 标签的操作分类
    public static final int TAG_ACTION_SET = 3;
    public static final int TAG_ACTION_ADD = 4;
    public static final int TAG_ACTION_DELETE = 5;
    public static final int TAG_ACTION_CLEAN_ALL = 6;
    public static final int TAG_ACTION_GET_ALL = 7;
    public static final int TAG_ACTION_CHECK = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //增加tag
        findViewById(R.id.bt_addtag).setOnClickListener(this);
        //设置tag
        findViewById(R.id.bt_settag).setOnClickListener(this);
        //删除tag
        findViewById(R.id.bt_deletetag).setOnClickListener(this);
        //获取所有tag
        findViewById(R.id.bt_getalltag).setOnClickListener(this);
        //清除所有tag
        findViewById(R.id.bt_cleantag).setOnClickListener(this);
        //查询tag绑定状态
        findViewById(R.id.bt_checktag).setOnClickListener(this);

        //设置alias
        findViewById(R.id.bt_setalias).setOnClickListener(this);
        //获取alias
        findViewById(R.id.bt_getalias).setOnClickListener(this);
        //删除alias
        findViewById(R.id.bt_deletealias).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int whichAction = -1;
        Set<String> tagsData = null;
        String aliasData = null;
        boolean isAliasAction = false;
        switch (v.getId()) {
            //增加tag
            case R.id.bt_addtag:
                whichAction = AliasTagsBean.TAG_ACTION_ADD;
                tagsData = getInPutTags();
                if (tagsData == null) {
                    return;
                }
                break;
            //设置tag
            case R.id.bt_settag:
                whichAction = AliasTagsBean.TAG_ACTION_SET;
                tagsData = getInPutTags();
                if (tagsData == null) {
                    return;
                }
                break;
            //删除tag
            case R.id.bt_deletetag:
                whichAction = AliasTagsBean.TAG_ACTION_DELETE;
                tagsData = getInPutTags();
                if (tagsData == null) {
                    return;
                }
                break;
            //获取所有tag
            case R.id.bt_getalltag:
                whichAction = AliasTagsBean.TAG_ACTION_GET_ALL;
                break;
            //清除所有tag
            case R.id.bt_cleantag:
                whichAction = AliasTagsBean.TAG_ACTION_CLEAN_ALL;
                break;
            //查询指定一个tag的状态
            case R.id.bt_checktag:
                whichAction = AliasTagsBean.TAG_ACTION_CHECK;
                tagsData = getInPutTags();
                if (tagsData == null) {
                    return;
                }
                break;

            //设置alias
            case R.id.bt_setalias:
                whichAction = AliasTagsBean.ALIAS_ACTION_SET;
                isAliasAction = true;
                aliasData = getInPutAlias();
                break;
            //获取alias
            case R.id.bt_getalias:
                whichAction = AliasTagsBean.ALIAS_ACTION_GET;
                isAliasAction = true;
                break;
            //删除alias
            case R.id.bt_deletealias:
                whichAction = AliasTagsBean.ALIAS_ACTION_DELETE;
                isAliasAction = true;
                break;
        }
        AliasTagsBean aliasTagsBean = new AliasTagsBean(whichAction, tagsData, aliasData, isAliasAction);
        AliasTagOperatorHelper.getInstance().handleAction(getApplication(), AliasTagOperatorHelper.sequence++, aliasTagsBean, new DistributeHandleResult() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void suggestAgainAction(AliasTagsBean aliasTagsBean) {
                System.out.println();
            }

            @Override
            public void actionSuccess(AliasTagsBean aliasTagsBean) {
                System.out.println();
            }
        });
    }

    /**
     * 获取输入的alias
     */
    private String getInPutAlias() {
        EditText aliasEdit = (EditText) findViewById(R.id.et_alias);
        String alias = aliasEdit.getText().toString().trim();
        if (TextUtils.isEmpty(alias)) {
            System.out.println();
            return null;
        }
        if (!JPUSHTools.isValidTagAndAlias(alias)) {
            System.out.println();
            return null;
        }
        return alias;
    }

    /**
     * 获取输入的tags
     */
    private Set<String> getInPutTags() {
        EditText tagEdit = (EditText) findViewById(R.id.et_tag);
        String tag = tagEdit.getText().toString().trim();
        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            System.out.println();
            return null;
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!JPUSHTools.isValidTagAndAlias(sTagItme)) {
                System.out.println();
                return null;
            }
            tagSet.add(sTagItme);
        }
        if (tagSet.isEmpty()) {
            System.out.println();
            return null;
        }
        return tagSet;
    }
}
