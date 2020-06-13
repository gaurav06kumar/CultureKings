package adapters;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import gauravkumar.com.cultureking.R;

public class ImageLoader {

    Context context;
    static ImageLoader imageLoader;

    public ImageLoader (Context context)
    {
        this.context = context;
    }

    public static synchronized ImageLoader getObject(Context context)
    {
        if(imageLoader==null)
        {
            imageLoader = new ImageLoader(context);
        }
        return imageLoader;
    }

    public void LoadImageFromUrl(ImageView view,String url)
    {
        Picasso.with(context).load("https://shoppie808.000webhostapp.com/CultureKingsRes/"+url).placeholder(R.mipmap.ck_icon).error(R.mipmap.ck_icon).into(view, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}
