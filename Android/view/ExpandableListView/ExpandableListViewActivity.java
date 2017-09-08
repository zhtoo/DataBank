package com.hs.live;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AndroidStudio on 2017/5/24.
 */

public class ExpandableListViewActivity extends AppCompatActivity {

    private ExpandableListView elv;
    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList = new String[]{"客户信息表", "客户需求表", "上传借款信息表照片"};
    private List<String> childrenList1 = new ArrayList<>();
    private List<String> childrenList2 = new ArrayList<>();
    private List<String> childrenList3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        elv = (ExpandableListView) findViewById(R.id.test_el);
        initialData();
        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter();
        elv.setAdapter(adapter);


    }

    private void initialData() {
        childrenList1.add("个人信息");
        childrenList1.add("配偶信息");
        childrenList1.add("共同借款人信息/保证人信息");
        childrenList1.add("紧急联系人信息");
        childrenList1.add("签字机日期");

        childrenList2.add("您的借款需求");
        childrenList2.add("*您的房产信息1");
        childrenList2.add("您的房产信息2");
        childrenList2.add("*您的车辆信息1");
        childrenList2.add("您的车辆信息2");
        childrenList2.add("申请人签字及日期");

        childrenList3.add("申请人签字及日期");
        childrenList3.add("申请人签字及日期");
        childrenList3.add("申请人签字及日期");

        dataset.put(parentList[0], childrenList1);
        dataset.put(parentList[1], childrenList2);
        dataset.put(parentList[2], childrenList3);
    }

    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        //  获得某个父项的某个子项
        @Override
        public Object getChild(int parentPos, int childPos) {
            return dataset.get(parentList[parentPos]).get(childPos);
        }

        //  获得父项的数量
        @Override
        public int getGroupCount() {
            return dataset.size();
        }

        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int parentPos) {
            if (parentPos == 2) {
                return 1;
            }
            return dataset.get(parentList[parentPos]).size();
        }

        //  获得某个父项
        @Override
        public Object getGroup(int parentPos) {
            return dataset.get(parentList[parentPos]);
        }

        //  获得某个父项的id
        @Override
        public long getGroupId(int parentPos) {
            return parentPos;
        }

        //  获得某个父项的某个子项的id
        @Override
        public long getChildId(int parentPos, int childPos) {
            return childPos;
        }

        //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
        @Override
        public boolean hasStableIds() {
            return false;
        }

        //  获得父项显示的view
        @Override
        public View getGroupView(int parentPos, boolean isExpanded, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.parent_item, null);
            }
            // view.setTag(R.layout.parent_item, parentPos);
            // view.setTag(R.layout.child_item, -1);
            TextView text = (TextView) view.findViewById(R.id.parent_title);
            text.setText(parentList[parentPos]);
            ImageView mImage = (ImageView) view.findViewById(R.id.parent_image);
            //判断是否已经打开列表
            if (isExpanded) {
                mImage.setBackgroundResource(R.drawable.ic_click);
            } else {
                mImage.setBackgroundResource(R.drawable.ic_normal);
            }
            return view;
        }

        //  获得子项显示的view
        @Override
        public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
            if (parentPos != 2) {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item, null);
            } else {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item_pic, null);
            }
            // view.setTag(R.layout.parent_item, parentPos);
            //  view.setTag(R.layout.child_item, childPos);
            if (parentPos != 2) {
                TextView text = (TextView) view.findViewById(R.id.child_title);
                text.setText(dataset.get(parentList[parentPos]).get(childPos));
                final int parentposion = parentPos;
                final int childposion = childPos;
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ExpandableListViewActivity.this, parentposion + "点到了内置的textview" + childposion, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                //处理上传图片的逻辑
            }
            return view;
        }

        //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }


}
