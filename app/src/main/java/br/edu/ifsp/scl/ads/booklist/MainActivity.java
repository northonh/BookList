package br.edu.ifsp.scl.ads.booklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

}