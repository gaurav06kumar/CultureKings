package adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gauravkumar.com.cultureking.R;

public class CustomlistAdapter extends BaseAdapter {

    private Context context;
    private List<SavedItemArrayclass> savedItemList;

    public CustomlistAdapter(Context context,ArrayList<SavedItemArrayclass> list)
    {
        this.context = context;
        savedItemList=list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem==null)
        {
            listItem = LayoutInflater.from(context).inflate(R.layout.custom_cart_list_item,parent,false);
            final SavedItemArrayclass savedItem = savedItemList.get(position);

            ImageView imageView = (ImageView) listItem.findViewById(R.id.image_li);
            ImageLoader.getObject(context).LoadImageFromUrl(imageView,savedItem.imageName+".png");

            TextView itemName = (TextView) listItem.findViewById(R.id.item_name_li);
            itemName.setText(savedItem.itemName);

            TextView itemPrice = (TextView) listItem.findViewById(R.id.item_price_li);
            itemPrice.setText(savedItem.price);

            TextView itemColor = (TextView) listItem.findViewById(R.id.item_description_li);
            itemColor.setText(savedItem.colors);

            final ProgressBar deletePBar = (ProgressBar) listItem.findViewById(R.id.delete_pbar);
            deletePBar.setVisibility(View.GONE);

            final ImageView deleteButton = (ImageView) listItem.findViewById(R.id.delete_li);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deletePBar.setVisibility(View.VISIBLE);
                    deleteButton.setEnabled(false);

                    Bundle bundle = new Bundle();
                    bundle.putString("sid",savedItem.sid);
                    savedItemList.remove(position);
                    WebApiAdapter.getObject(context).fireServerApi(8,bundle);
                    notifyDataSetChanged();
                }
            });

            TextView editButton = (TextView) listItem.findViewById(R.id.item_edit_li);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "working on edit button", Toast.LENGTH_SHORT).show();
                }
            });


        }
        return listItem;
    }

    @Nullable
    @Override
    public SavedItemArrayclass getItem(int position) {
        return savedItemList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getCount() {
        return (savedItemList.size());
    }
}
