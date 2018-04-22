package com.example.admin.banthuocdx.Dichvu.admin;

import android.widget.Filter;

import com.example.admin.banthuocdx.Dichvu.listthuoc.AdapterThuoc;
import com.example.admin.banthuocdx.Doituong.thuoc;

import java.util.ArrayList;

public class timkiemAdapterA extends Filter {
    AdapterThuocA adapterThuoc;
    ArrayList<thuoc>listthuoc;

    public timkiemAdapterA(AdapterThuocA adapterThuoc, ArrayList<thuoc> listthuoc) {
        this.adapterThuoc = adapterThuoc;
        this.listthuoc = listthuoc;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint!=null && constraint.length()>0)
        {
            constraint=constraint.toString();
            ArrayList<thuoc> timlist= new ArrayList<>();
            for (int i=0;i<listthuoc.size();i++)
            {
                if (listthuoc.get(i).getTenThuoc().contains(constraint))
                {
                    timlist.add(listthuoc.get(i));

                }
            }
            filterResults.count=timlist.size();
            filterResults.values=timlist;
        }
        else
        {
            filterResults.count=listthuoc.size();
            filterResults.values=listthuoc;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterThuoc.thuocArrayList=((ArrayList<thuoc>)results.values);
        adapterThuoc.notifyDataSetChanged();
    }
}
