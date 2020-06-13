package adapters;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemsAdapter {

    static Context con;
    static ItemsAdapter itemsAdapter;
    ItemArrayClass items;
    ArrayList<ItemArrayClass> list;
    boolean itemsHere = false;

    SavedItemArrayclass savedItem;
    ArrayList<SavedItemArrayclass> savedList;

    public ItemsAdapter(Context context)
    {
        con = context;
        list = new ArrayList<>();
        savedList = new ArrayList<>();
    }

    public static synchronized ItemsAdapter getObject(Context context)
    {
        con = context;
        if(itemsAdapter==null)
        {
            itemsAdapter = new ItemsAdapter(context);
        }
        return itemsAdapter;
    }
    public void setItemHereTrue()
    {
        itemsHere = true;
    }
    public boolean isItemsPresent(){
        return itemsHere;
    }

    public void saveAllItems(String response)
    {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i =0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                items = new ItemArrayClass();
                items.itemID = jsonObject.getString("iid");
                items.itemName = jsonObject.getString("itemName");
                items.colors = jsonObject.getString("color");
                items.sizes = jsonObject.getString("size");
                items.price = jsonObject.getString("price");
                items.gender = jsonObject.getString("gender");
                items.category = jsonObject.getString("category");
                items.imageName = jsonObject.getString("image");
                items.description = jsonObject.getString("description");
                list.add(items);
            }
          }
        catch (Exception e)
        {
               AlertAdapter.getObject(con).createMessageAlert(e.getMessage());
        }
    }

    public ArrayList<ItemArrayClass> getItems(String gender, String category)
    {
        ArrayList<ItemArrayClass> arrayList = new ArrayList<>();
        for(int i = 0;i<32;i++)
        {
            items = list.get(i);
            if(items.gender.equalsIgnoreCase(gender) && items.category.equalsIgnoreCase(category))
            {
                arrayList.add(items);
               }
        }
        return arrayList;
    }

    public void saveCartItems(String response)
    {
        savedList.clear();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i =0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                savedItem = new SavedItemArrayclass();
                savedItem.itemID = jsonObject.getString("iid");
                savedItem.itemName = jsonObject.getString("itemName");
                savedItem.colors = jsonObject.getString("color");
                savedItem.sizes = jsonObject.getString("size");
                savedItem.price = jsonObject.getString("price");
                savedItem.gender = jsonObject.getString("gender");
                savedItem.category = jsonObject.getString("category");
                savedItem.imageName = jsonObject.getString("image");
                savedItem.sid = jsonObject.getString("sid");
                savedList.add(savedItem);
            }
        }
        catch (Exception e)
        {
            AlertAdapter.getObject(con).createMessageAlert(e.getMessage());
        }
    }

    public boolean deleteSavedItem(String sid)
    {
        boolean flag = false;
        for(int i = 0;i<savedList.size();i++)
        {
            if(savedList.get(i).sid==sid)
            {
                savedList.remove(i);
                flag = true;
            }
        }
        return flag;
    }

    public String getCartTotalPrice()
    {
        int totalPrice = 0;
        for (int i = 0;i<savedList.size();i++)
        {
            totalPrice = totalPrice + Integer.parseInt(savedList.get(i).price);
        }
        return String.valueOf(totalPrice);
    }

    public ArrayList<SavedItemArrayclass> getSavedItemsList()
    {
        return savedList;
    }
}


