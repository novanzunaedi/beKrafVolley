package nozstudio.bekrafvolley.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nozstudio.bekrafvolley.R;
import nozstudio.bekrafvolley.model.Cuaca;

/**
 * Created by CLient-Pc on 29/06/2016.
 */
public class CustomAdapter extends BaseAdapter {

    private Activity ac;
    private List<Cuaca> cuAcaItem;
    private LayoutInflater inflater;

    public CustomAdapter(Activity ac, List<Cuaca> cuAcaItem) {
        this.ac = ac;
        this.cuAcaItem = cuAcaItem;
    }

    @Override
    public int getCount() {
        return cuAcaItem.size();
    }

    @Override
    public Object getItem(int position) {
        return cuAcaItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        inflater = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_datetime, null);

        Cuaca cuacaItem = cuAcaItem.get(position);

        TextView tvdateTime = (TextView) view.findViewById(R.id.tvDateTime);
        tvdateTime.setText(cuacaItem.getSdatetime());

        return view;
    }
}
