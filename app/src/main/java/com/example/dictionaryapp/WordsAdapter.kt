package com.example.dictionaryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class WordsAdapter(private val context:Context,private val wordsList:List<Words>)
    :RecyclerView.Adapter<WordsAdapter.CardDesignHosting>(){

    inner class CardDesignHosting(design: View) : RecyclerView.ViewHolder(design) {

        var word_card: CardView
        var textViewEnglish: TextView
        var textViewTurkish: TextView

        init {
            word_card =design.findViewById(R.id.word_card)
            textViewEnglish =design.findViewById(R.id.textViewEnglish)
            textViewTurkish =design.findViewById(R.id.textViewTurkish)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHosting {
        val design=LayoutInflater.from(context).inflate(R.layout.card_design,parent,false)
        return  CardDesignHosting(design)
    }

    override fun getItemCount(): Int {
        return wordsList.size

    }

    override fun onBindViewHolder(holder: CardDesignHosting, position: Int) {
        val word=wordsList.get(position)

        holder.textViewEnglish.text=word.ingilizce
        holder.textViewTurkish.text=word.turkce

        holder.word_card.setOnClickListener{
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("object",word)
            context.startActivity(intent)

        }



    }
}