package com.example.notesapplication;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Listview_Adapter extends BaseAdapter {

    Activity context;
    ArrayList<Notes> arrayList;

    public Listview_Adapter(Activity context, ArrayList<Notes> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.notes_layout,null);

        TextView title = convertView.findViewById(R.id.title_of_notes);
        TextView date = convertView.findViewById(R.id.date_of_notes);
        TextView descriptionn = convertView.findViewById(R.id.description_of_notes);
        TextView time = convertView.findViewById(R.id.time_of_notes);

        title.setText(arrayList.get(position).getTitle());
        date.setText(arrayList.get(position).getDate());
        descriptionn.setText(arrayList.get(position).getDescription());
        time.setText(arrayList.get(position).getTime());

        return convertView;
    }

    public void updateList(ArrayList<Notes> filteredList)
    {
        arrayList = filteredList;
        notifyDataSetChanged();
    }
}
