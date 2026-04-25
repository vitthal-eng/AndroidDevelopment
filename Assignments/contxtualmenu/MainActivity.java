package com.example.contxtualmenu;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    ActionMode actionMode;
    int selectedPosition = -1;

    String[] data = {"Item 1", "Item 2", "Item 3", "Item 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);

        // Floating Context Menu
        registerForContextMenu(textView);

        // ListView setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        // Contextual Action Mode (CAB)
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) return false;

            selectedPosition = position;

            actionMode = startActionMode(actionModeCallback);
            view.setSelected(true);

            return true;
        });
    }

    // Floating Context Menu
    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v,
                                    android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit) {
            Toast.makeText(this, "Edit clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    // CAB (Contextual Action Bar)
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.cab_menu, menu);
            mode.setTitle("1 Selected");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            if (item.getItemId() == R.id.share) {
                Toast.makeText(MainActivity.this, "Share clicked", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.delete) {
                Toast.makeText(MainActivity.this, "Delete clicked", Toast.LENGTH_SHORT).show();
            }

            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}