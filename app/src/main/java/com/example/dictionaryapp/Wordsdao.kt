package com.example.dictionaryapp

class Wordsdao {

    fun allWords(db:DatabaseHelper):ArrayList<Words>{
        val wordsList=ArrayList<Words>()
        val db=db.writableDatabase
        val c=db.rawQuery("SELECT * FROM kelimeler",null)

        while (c.moveToNext()){
            val word=Words(c.getInt(c.getColumnIndexOrThrow("kelime_id"))
                ,c.getString(c.getColumnIndexOrThrow("ingilizce"))
                        ,c.getString(c.getColumnIndexOrThrow("turkce")))
            wordsList.add(word)
        }
        return wordsList
    }
    fun makesearch(db:DatabaseHelper,searchWord:String):ArrayList<Words>{
        val wordsList=ArrayList<Words>()
        val db=db.writableDatabase
        val c=db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%$searchWord%'",null)

        while (c.moveToNext()){
            val word=Words(c.getInt(c.getColumnIndexOrThrow("kelime_id"))
                ,c.getString(c.getColumnIndexOrThrow("ingilizce"))
                ,c.getString(c.getColumnIndexOrThrow("turkce")))
            wordsList.add(word)
        }
        return wordsList
    }
}