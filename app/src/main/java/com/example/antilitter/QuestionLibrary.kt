package com.example.antilitter

class QuestionLibrary {
    var data = listOf(
        Pair(R.drawable.compost_apple, "compost"),
        Pair(R.drawable.compost_bones, "compost"),
        Pair(R.drawable.compost_burger, "compost"),
        Pair(R.drawable.compost_chicken, "compost"),
        Pair(R.drawable.compost_coffee, "compost"),
        Pair(R.drawable.compost_papertowel, "compost"),
        Pair(R.drawable.compost_tea, "compost"),
        Pair(R.drawable.recycle_cans, "recycle"),
        Pair(R.drawable.recycle_cardboardbox, "recycle"),
        Pair(R.drawable.recycle_glass, "recycle"),
        Pair(R.drawable.recycle_milk, "recycle"),
        Pair(R.drawable.recycle_newspaper, "recycle"),
        Pair(R.drawable.recycle_soda, "recycle"),
        Pair(R.drawable.trash_bag, "trash"),
        Pair(R.drawable.trash_bandaid, "trash"),
        Pair(R.drawable.trash_battery, "trash"),
        Pair(R.drawable.trash_chips, "trash"),
        Pair(R.drawable.trash_condiments, "trash"),
        Pair(R.drawable.trash_cup, "trash"),
        Pair(R.drawable.trash_utensils, "trash")
    )

    fun getImage(idx: Int): Int {
        return data.get(idx).first
    }

    fun getType(idx: Int): String {
        return data.get(idx).second
    }
}