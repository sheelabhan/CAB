package adapter;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymart.ItemaddtocartActivity;
import com.example.mymart.R;

import java.util.List;

import Fragments.CartFragment;
import Fragments.FeedbackFragment;
import Fragments.MoreFragment;
import Interface.Api;
import Url.Url;
import createchannel.CreateChannel;
import de.hdodenhof.circleimageview.CircleImageView;
import model.CartModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    CartAdapter a = this;
    Context context;
    List<CartModel> cartModels;



    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_cart_detailed, viewGroup, false);
        return new CartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, int i) {

        final CartModel model = cartModels.get(i);

        cartViewHolder.iname.setText(model.getIname());
        cartViewHolder.iprice.setText(model.getIprice());
        cartViewHolder.idesc.setText(model.getIdescription());
        cartViewHolder.icategory.setText(model.getIcategory());
        cartViewHolder.iid.setText(model.getId());
    }
    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {


        TextView iname, iprice, idesc, icategory,iid;
        Button button;

        public CartViewHolder(@NonNull View cartView) {
            super(cartView);


            iname = cartView.findViewById(R.id.name3);
            iprice = cartView.findViewById(R.id.price3);
            idesc = cartView.findViewById(R.id.description3);
            icategory = cartView.findViewById(R.id.category3);
            button=cartView.findViewById(R.id.btndelete);
            iid=cartView.findViewById(R.id.iid);


        }
    }


//        CartFragment cartFragment=new CartFragment();
//        FragmentManager fragmentManager=cartFragment.getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_frame,cartFragment);
//        fragmentTransaction.commit();
    }





