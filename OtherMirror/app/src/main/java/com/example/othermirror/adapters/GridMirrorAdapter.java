package com.example.othermirror.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.othermirror.R;

public class GridMirrorAdapter extends BaseAdapter {
    private static final int[] imageIndex = {R.drawable.time, R.drawable.calendar, R.drawable.sun, R.drawable.newspaper, R.drawable.quotes, R.drawable.weight, 0};
    private int[] gridImagesIndex;
    private Context mContext;


    public GridMirrorAdapter(Context mContext, int[] images) {
        this.gridImagesIndex = images;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return gridImagesIndex.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int gridPosition, View convertView, final ViewGroup parent) {
        final Button mBtn;
        AlertDialog alertDialog;
        final ImageView imageView;

        if (convertView == null)
        {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.mirror_config_items, null);
        }

        // Find IDs
        mBtn = convertView.findViewById(R.id.button);
        imageView = convertView.findViewById(R.id.mirror_config_image);

        //Set image
        imageView.setImageResource(imageIndex[gridImagesIndex[gridPosition]]);

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Choose Widgets");

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_spinner,parent,false);
                final Spinner mSpinner = view.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,
                        mContext.getResources().getStringArray(R.array.list_widgets));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                mSpinner.setSelection(gridImagesIndex[gridPosition]);   // Pre-set image for spinner depending on index from grid

                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        gridImagesIndex[gridPosition] = position;           // Set of selection
                        imageView.setImageResource(imageIndex[position]);   // Set image from selection
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                builder.setView(view);
                builder.create();
                builder.show();
            }
        });
        
        
        
        return convertView;
    }






    /*
}
    Context context;
    private ImageView widget_img;
    //private int [] gridImagesIndex;
    View view;
    private ConfigFile configFile;
    private List<ConfigFile> configList;
    LayoutInflater inflater;
    int gridImagesIndex[];

    public GridMirrorAdapter(Context context, int[] gridImagesIndex) {
        this.context = context;
        this.gridImagesIndex = gridImagesIndex;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gridImagesIndex.length;
    }

    @Override
    public Object getItem(int position) {
        return gridImagesIndex[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private int getImageForPosition(int position){
        int img_value = gridImagesIndex[position];
        if(context.equals("time")){
            return R.drawable.time;
        }
        else if(context.equals("weather")){
            return R.drawable.sun;
        }else {
            return R.drawable.bluetooth;
        }
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Log.d("position", String.valueOf(gridImagesIndex[position]));

        Spinner mSpinner;
        Button btn;


        /*
        if(convertView == null){
            convertView = inflater.inflate(R.layout.mirror_config_items, parent,false);
            widget_img = (ImageView) convertView.findViewById(R.id.mirror_config_image);
            widget_img.setImageResource(gridImagesIndex[position]);
            Log.d("position", String.valueOf(gridImagesIndex[position]));

        Button button = convertView.findViewById(R.id.button);


        final View finalConvertView = convertView;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("dinmor", "KOM NUUUUU");
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Widgets");

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_spinner,parent,false);
                final Spinner mSpinner = view.findViewById(R.id.spinner);
                //Toast.makeText(context, "click on item" + position, Toast.LENGTH_SHORT).show();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,
                        context.getResources().getStringArray(R.array.list_widgets));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Toast.makeText(context, "click on item" + position, Toast.LENGTH_SHORT).show();

                        Log.d("dinmor",(mSpinner.getSelectedItem().toString()));
                        String WhatItem = mSpinner.getSelectedItem().toString();

                        widget_img.setImageResource(gridImagesIndex[position]);

                        if(WhatItem.contains("time")){
                            widget_img.setImageResource(R.drawable.time);
                        }
                        else if(WhatItem.contains("calendar")){
                            widget_img.setImageResource(R.drawable.calendar);
                        }
                        else if(WhatItem.contains("weather")){
                            widget_img.setImageResource(R.drawable.sun);
                        }
                        else if(WhatItem.contains("news")){
                            widget_img.setImageResource(R.drawable.newspaper);

                        }
                        else if(WhatItem.contains("quotes")){
                            widget_img.setImageResource(R.drawable.quotes);

                        }
                        else if(WhatItem.contains("weight")){
                            widget_img.setImageResource(R.drawable.weight);
                        }

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                builder.setNeutralButton("SAVE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return convertView;
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{
        public View mView;
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }*/


}
