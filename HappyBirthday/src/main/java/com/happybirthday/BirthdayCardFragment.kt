package com.happybirthday

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Simple fragment that inflates res/layout/fragment_birthday_card.xml
 */
class BirthdayCardFragment : Fragment(R.layout.fragment_birthday_card) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Optional: wire up views here, e.g.
        // val title = view.findViewById<TextView>(R.id.happy_bday_title)
        // title.text = "Custom message"
    }
}
