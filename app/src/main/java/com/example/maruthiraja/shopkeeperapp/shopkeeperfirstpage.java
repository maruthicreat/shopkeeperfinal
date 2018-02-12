package com.example.maruthiraja.shopkeeperapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

public class shopkeeperfirstpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private MaterialSearchView searchView;
    private RecyclerView itemlist;
    private DatabaseReference mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeperfirstpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("shop_details");

        itemlist = (RecyclerView) findViewById(R.id.item_list);
        itemlist.setHasFixedSize(true);
        itemlist.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
       // searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(shopkeeperfirstpage.this,upload_items.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseRecyclerAdapter<ItemShow,ItemHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ItemShow, ItemHolder>(
                ItemShow.class,
                R.layout.item_list,
                ItemHolder.class,
                mdatabase

        ) {

            @Override
            protected void populateViewHolder(ItemHolder viewHolder, ItemShow model, int position) {
                viewHolder.setItemName(model.getTitle());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());
            }
        };

        itemlist.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public static class ItemHolder extends RecyclerView.ViewHolder{

        View mview;

        public ItemHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setItemName(String name)
        {
            System.out.println("name"+name);
            TextView item_n = (TextView) mview.findViewById(R.id.item_name);
            item_n.setText(name);
        }

        public void setPrice(String price)
        {
            System.out.println("price"+price);
            TextView item_p = (TextView) mview.findViewById(R.id.item_price);
            item_p.setText(price);
        }

        public void setDescription(String desc)
        {
            System.out.println("desc"+desc);
            TextView item_d = (TextView) mview.findViewById(R.id.item_description);
            item_d.setText(desc);
        }

        public void setImage(String image)
        {
            System.out.println("image"+image);
            ImageView imageView = (ImageView) mview.findViewById(R.id.item_image);
            Picasso.with(mview.getContext())
                    .load(image)
                    .into(imageView);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shopkeeperfirstpage, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_logout)
        {
            mAuth.signOut();
            startActivity( new Intent(shopkeeperfirstpage.this,MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
