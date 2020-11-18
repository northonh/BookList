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