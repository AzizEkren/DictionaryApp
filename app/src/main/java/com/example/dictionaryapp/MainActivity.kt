package com.example.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var wordsList:ArrayList<Words>
    private lateinit var adapter: WordsAdapter
    private lateinit var db: DatabaseHelper


    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databasecopy()

        binding.toolbar.title="Dictionary App"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)

        binding.rv.layoutManager=LinearLayoutManager(this)

        db= DatabaseHelper(this)
        wordsList=Wordsdao().allWords(db)

        adapter= WordsAdapter(this,wordsList)

        binding.rv.adapter=adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val item=menu?.findItem(R.id.action_search)
        val searchView=item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        Log.e("sent search",query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        Log.e("enter letters",newText)
        return true
    }

    fun databasecopy(){
        val copHelper=DatabaseCopyHelper(this)
        try {
            copHelper.createDataBase()
            copHelper.openDataBase()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun search(searchWord:String){
        wordsList=Wordsdao().makesearch(db,searchWord)

        adapter= WordsAdapter(this,wordsList)

        binding.rv.adapter=adapter
    }
}