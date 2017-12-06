package com.example.ltmillar.cardmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rajat Bhatia on 12/6/2017.
 */

public class CardAdapter extends BaseAdapter {
    private ArrayList<Card> arrayList;
    private Context context;

    public CardAdapter(Context context, ArrayList<Card> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater li =LayoutInflater.from(context);

        if (convertView == null) {
            convertView = li.inflate(R.layout.card_list, null);
            viewHolder = new ViewHolder();
            viewHolder.tvCardName = (TextView) convertView.findViewById(R.id.textCardName);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvCardName.setText(arrayList.get(position).getCardName());

        return convertView;
    }

    private class ViewHolder{
        TextView tvCardName;
    }
}
