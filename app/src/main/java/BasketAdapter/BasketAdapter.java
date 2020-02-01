package BasketAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.weshopapplication.R;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.CartViewHolder> {
    private Context context;
    private List<String> productsList;

    public BasketAdapter(Context context, List<String> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.basket_item_layout, parent, false);
        return new CartViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.quantityAmount.setNumber(String.valueOf(productsList.get(position)) + 1);
        holder.productPrice.setText(new StringBuilder("£").append(productsList.get(position) + 800));
        holder.productName.setText(productsList.get(position) + "iPhone X");

        holder.quantityAmount.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size(); // Get the size
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView productName;
        private TextView productColour;
        private TextView productPrice;

        private ElegantNumberButton quantityAmount;


        public CartViewHolder(View itemView) {

            super(itemView);

            this.imgProduct = itemView.findViewById(R.id.product_image);
            this.productName = itemView.findViewById(R.id.txt_product_name);
            this.productColour = itemView.findViewById(R.id.txt_colour);
            this.productPrice = itemView.findViewById(R.id.txt_price);
            this.quantityAmount = itemView.findViewById(R.id.txt_quantity_amount);

        }
    }
}
