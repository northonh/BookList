package br.edu.ifsp.scl.ads.booklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.ads.booklist.databinding.ActivityNewBookBinding

class NewBookActivity : AppCompatActivity() {
    // View Binding
    private lateinit var activityNewBookBinding: ActivityNewBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar leiaute com view binding
        activityNewBookBinding = ActivityNewBookBinding.inflate(layoutInflater)
        setContentView(activityNewBookBinding.root)

        val book = intent.getParcelableExtra<Book>(MainActivity.EXTRA_BOOK)
        if (book != null) {
            // Edição ou Visualização
            with (activityNewBookBinding) {
                titleEt.setText(book.title)
                isbnEt.setText(book.isbn)
                firstAuthorEt.setText(book.firstAuthor)
                publishingCompanyEt.setText(book.publishingCompany)
                editionEt.setText(book.edition.toString())
                pagesEt.setText(book.pages.toString())
            }

            val action = intent.action
            if (action != null && action == MainActivity.ACTION_VIEW_BOOK) {
                // Visualização
                supportActionBar?.subtitle = "Book details"
                with (activityNewBookBinding) {
                    titleEt.isEnabled = false
                    isbnEt.isEnabled = false
                    firstAuthorEt.isEnabled = false
                    publishingCompanyEt.isEnabled = false
                    editionEt.isEnabled = false
                    pagesEt.isEnabled = false
                    saveBt.visibility = View.GONE
                }
            }
            else {
                // Edição
                supportActionBar?.subtitle = "Edit book"
            }
        }
        else{
            supportActionBar?.subtitle = "New book"
        }
    }

    fun onClick(view: View) {
        if (view.id == R.id.saveBt) {
            val book = Book(
                    activityNewBookBinding.titleEt.text.toString(),
                    activityNewBookBinding.isbnEt.text.toString(),
                    activityNewBookBinding.firstAuthorEt.text.toString(),
                    activityNewBookBinding.publishingCompanyEt.text.toString(),
                    activityNewBookBinding.editionEt.text.toString().toInt(),
                    activityNewBookBinding.pagesEt.text.toString().toInt()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_BOOK, book)
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}