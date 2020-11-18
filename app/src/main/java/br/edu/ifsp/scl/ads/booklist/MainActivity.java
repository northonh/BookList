package br.edu.ifsp.scl.ads.booklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.scl.ads.booklist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // Data source de Adapter
    private List<Book> bookList;

    // Adapter do ListView
    private ArrayAdapter<String> bookListAdapter;

    // View Binding
    private ActivityMainBinding activityMainBinding;

    // Constante para extra de Book
    public static final String EXTRA_BOOK = "EXTRA_BOOK";

    // Constante de request code para NewBookActivity
    private final int NEW_BOOK_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Infla leiaute com view binding e setar o root como leiaute
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        // Instanciar a bookList
        bookList = new ArrayList();
        initializeBookList();

        // Instanciar bookListAdapter
        bookListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bookList);

        // Setando bookListAdapter como Adapter do bookListLv
        activityMainBinding.bookListLv.setAdapter(bookListAdapter);
    }

    // Método que popula o bookList para teste
    private void initializeBookList() {
        for (int i = 0; i < 10; i++) {
            bookList.add(
                    new Book(
                            "Titulo" + i,
                            "ISBN" + i,
                            "Autor" + i,
                            "Editora" + i,
                            i,
                            i
                    )
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.newBookMi) {
            Intent newBookIntent = new Intent(this, NewBookActivity.class);
            startActivityForResult(newBookIntent, NEW_BOOK_REQUEST_CODE);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_BOOK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Book newBook = data.getParcelableExtra(MainActivity.EXTRA_BOOK);
            bookList.add(newBook);
            bookListAdapter.notifyDataSetChanged();
        }
    }
}