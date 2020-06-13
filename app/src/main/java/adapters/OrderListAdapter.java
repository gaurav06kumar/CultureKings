package adapters;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import gauravkumar.com.cultureking.R;

public class OrderListAdapter extends BaseAdapter {
    private Context context;
    private List<OrderArrayClass> orders;

    public OrderListAdapter(Context context,List<OrderArrayClass> orderList)
    {
        this.context = context;
        orders = orderList;
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public OrderArrayClass getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listItem = view;
        if(listItem == null)
        {

            listItem = LayoutInflater.from(context).inflate(R.layout.custom_order_list,viewGroup,false);
            final OrderArrayClass order =  orders.get(i);
            TextView orderNum = (TextView)listItem.findViewById(R.id.order_number_li);
            TextView orderItems = (TextView)listItem.findViewById(R.id.order_items_li);
            TextView orderPrice = (TextView)listItem.findViewById(R.id.order_price_li);

            orderItems.setMovementMethod(new ScrollingMovementMethod());


            orderNum.setText("ORDER NUMBER  #"+order.orderNumber);
            orderPrice.setText("NZD $"+order.totalPrice);
        }

        return listItem ;
    }
}
