package com.ashbab.ashbabapp.ui.productPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashbab.ashbabapp.R;
import com.ashbab.ashbabapp.data.model.Product;
import com.ashbab.ashbabapp.ui.home.MainActivity;
import com.bumptech.glide.Glide;

public class ProductDetails extends AppCompatActivity
{
    private static final String LOG_TAG = ProductDetails.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // find the layout contents
        final ImageView productImage = findViewById(R.id.product_image_view_details);
        final TextView productName = findViewById(R.id.product_text_view_details);
        final TextView productPrice = findViewById(R.id.price_text_view_details);
        final TextView productCategory = findViewById(R.id.category_tag_details);
        final TextView productDescription = findViewById(R.id.description_details);
        final Button arButton = findViewById(R.id.ar_button_details);

        // Obtain a new or prior instance of HotStockViewModel from the
        // ViewModelProviders utility class.
        ProductDetailsViewModel viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);

        LiveData<Product> liveData = viewModel.getProductDetailsLiveData();

        liveData.observe(this, product ->
        {
            Log.v(LOG_TAG, "Data change detected");

            if (product != null)
            {
                // update the UI here
                Glide.with(productImage.getContext()).load(product.getImageUrl()).into(productImage);
                productName.setText(product.getProductName());
                productPrice.setText(String.valueOf(product.getProductPrice()));
                productCategory.setText(product.getCategory());
                productDescription.setText(product.getDescription());

                Log.v(LOG_TAG, product.getProductName() + " has been added to loaded successfully");
            }
        });

        arButton.setOnClickListener(v ->
        {
            Intent arCameraIntent = new Intent(ProductDetails.this, ArCameraActivity.class);
            startActivity(arCameraIntent);
        });
    }
}
