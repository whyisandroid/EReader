package com.ereader.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ereader.client.R;
import com.ereader.client.entities.Comment;

public class BookSPAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<Comment> mList ;

	public BookSPAdapter(Context mContext,List<Comment> list) {
		inflater=LayoutInflater.from(mContext);
		mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Comment comment = mList.get(position);
		ViewHolder holder;
		if(convertView == null){
			convertView =inflater.inflate(R.layout.book_sp_item, null);
			holder = new ViewHolder();
			holder.findView(convertView);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}	
		holder.rb_book_star.setRating(Float.valueOf(comment.getScore()));
		holder.tv_mybook_cate.setText(comment.getNickname());
		holder.tv_mybook_title.setText(comment.getTitle());
		holder.tv_book_sp_content.setText(comment.getContent());
		
		if(holder.tv_book_sp_content.getLineCount() == 5){
			holder.tv_book_sp_more.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
	class ViewHolder{
		private RatingBar rb_book_star;
		private TextView tv_mybook_cate;
		private TextView tv_mybook_title;
		private TextView tv_book_sp_content;
		private TextView tv_book_sp_more;
		public void findView(View view){
			rb_book_star = (RatingBar)view.findViewById(R.id.rb_book_star);
			tv_mybook_cate = (TextView)view.findViewById(R.id.tv_mybook_cate);
			tv_mybook_title = (TextView)view.findViewById(R.id.tv_mybook_title);
			tv_book_sp_content = (TextView)view.findViewById(R.id.tv_book_sp_content);
			tv_book_sp_more = (TextView)view.findViewById(R.id.tv_book_sp_more);
		}
	}

}
