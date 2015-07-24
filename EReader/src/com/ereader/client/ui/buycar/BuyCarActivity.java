package com.ereader.client.ui.buycar;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ereader.client.EReaderApplication;
import com.ereader.client.R;
import com.ereader.client.entities.Book;
import com.ereader.client.entities.json.BookOnlyResp;
import com.ereader.client.service.AppController;
import com.ereader.client.ui.BaseActivity;
import com.ereader.client.ui.adapter.BuyCarAdapter;
import com.ereader.client.ui.login.LoginActivity;
import com.ereader.common.util.IntentUtil;
import com.ereader.common.util.ProgressDialogUtil;
import com.ereader.common.util.StringUtil;

// 购物车
public class BuyCarActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView tv_buy_money;
	private ListView lv_buy_car;
	private List<Book> mList = new ArrayList<Book>();
	private BuyCarAdapter adapter;
	private Button bt_buy_go;
	private RadioButton rb_car_all;
	private int buyNum = 0;
	private String money = "0";
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				mList.clear();
				mList.addAll((List<Book>)controller.getContext().getBusinessData("BuyCarResp"));
				adapter.notifyDataSetChanged();
				break;
			case 1:
				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_car_layout);
		controller = AppController.getController(this);
		findView();
		initView();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getCar();
	}
	private void getCar() {
		if(!EReaderApplication.getInstance().isLogin()){
			IntentUtil.intent(BuyCarActivity.this, LoginActivity.class);
		}else{
			ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					controller.buyCar(mHandler);
					ProgressDialogUtil.closeProgressDialog();
				}
			}).start();
		}
	}
	/**
	 * 
	  * 方法描述：FindView
	  * @author: why
	  * @time: 2015-2-10 下午1:37:06
	 */
	private void findView() {
		lv_buy_car = (ListView)findViewById(R.id.lv_buy_car);
		bt_buy_go = (Button)findViewById(R.id.bt_buy_go);
		tv_buy_money = (TextView)findViewById(R.id.tv_buy_money);
		rb_car_all = (RadioButton)findViewById(R.id.rb_car_all);
	}
	

	/**
	 * 
	  * 方法描述：初始化 View
	  * @author: why
	  * @time: 2015-2-10 下午1:37:06
	 */
	private void initView() {
		((TextView) findViewById(R.id.tv_main_top_title)).setText("购物车");
		bt_buy_go.setOnClickListener(this);
		BookOnlyResp resp  = (BookOnlyResp)EReaderApplication.getInstance().getBuyCar();
		if(resp != null){
			mList.clear();
			mList.addAll(resp.getData());

			for (int i = 0; i < mList.size(); i++) {
				if(mList.get(i).isSelect()){
					buyNum++;
					money = StringUtil.addMoney(money, mList.get(i).getPrice());
				}
			}
		}
		tv_buy_money.setText("¥ "+money);
		bt_buy_go.setText("结算（"+buyNum+")");
		adapter = new BuyCarAdapter(this, mList,mHandler);
		lv_buy_car.setAdapter(adapter);
		rb_car_all.setOnCheckedChangeListener(carAllListener);
		rb_car_all.setOnClickListener(this);
	}
	private OnCheckedChangeListener carAllListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
				if(isChecked){
					for (int i = 0; i < mList.size(); i++) {
						mList.get(i).setSelect(true);
					}
					adapter.notifyDataSetChanged();
				}
		}
	};
   
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case  R.id.bt_buy_go:
			
			break;
		case  R.id.rb_car_all:
			if(rb_car_all.isChecked()){
				money = "0";
				for (int i = 0; i < mList.size(); i++) {
					buyNum++;
					mList.get(i).setSelect(true);
					money = StringUtil.addMoney(money, mList.get(i).getPrice());
				}
				tv_buy_money.setText("¥ "+money);
				bt_buy_go.setText("结算（"+buyNum+")");
				adapter.notifyDataSetChanged();
			}
	
			break;
		default:
			break;
		}
	}
}
