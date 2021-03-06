package com.ereader.client.ui.my;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ereader.client.R;
import com.ereader.client.entities.Category;
import com.ereader.client.service.AppController;
import com.ereader.client.ui.BaseFragmentActivity;
import com.ereader.client.ui.adapter.BookTabsAdapter;
import com.ereader.client.ui.adapter.OrderFragsAdapter;
import com.ereader.client.ui.view.ScrollingTabsView;
// 订单
public class OrderActivity extends BaseFragmentActivity implements OnClickListener {
	private AppController controller;
	private ScrollingTabsView st_order;
	private ViewPager vp_order;
	private Button main_top_right;
	private List<Category> mListTitle;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_layout);
		controller = AppController.getController(this);
		findView();
		initView();
	}
	/**
	 * 
	  * 方法描述：FindView
	  * @author: why
	  * @time: 2015-2-10 下午1:37:06
	 */
	private void findView() {
		main_top_right = (Button)findViewById(R.id.main_top_right);
		st_order = (ScrollingTabsView)findViewById(R.id.st_order);
		vp_order = (ViewPager)findViewById(R.id.vp_order);
	}
	

	/**
	 * 
	  * 方法描述：初始化 View
	  * @author: why
	  * @time: 2015-2-10 下午1:37:06
	 */
	private void initView() {
		((TextView) findViewById(R.id.tv_main_top_title)).setText("我的订单");
		mListTitle = new ArrayList<Category>();
			mListTitle.add(new Category("全部","1"));
			mListTitle.add(new Category("正在处理","2"));
			mListTitle.add(new Category("已取消","3"));
		OrderFragsAdapter orderAdapter = new OrderFragsAdapter(getSupportFragmentManager(),mListTitle.size());
		vp_order.setAdapter(orderAdapter);
		
		// 设置缓存fragment的数量
		vp_order.setOffscreenPageLimit(2);
		vp_order.setCurrentItem(0);
		vp_order.setPageMargin(4);
		
		
		BookTabsAdapter adapter = new BookTabsAdapter(this,mListTitle);
		st_order.setAdapter(adapter);
		st_order.setViewPager(vp_order);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case  R.id.main_top_right:
			break;
		default:
			break;
		}
	}
}
