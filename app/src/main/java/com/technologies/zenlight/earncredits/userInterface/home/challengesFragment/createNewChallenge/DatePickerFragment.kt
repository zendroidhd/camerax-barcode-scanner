package com.technologies.zenlight.earncredits.userInterface.home.challengesFragment.createNewChallenge

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        var dateListener: DateListener? = null
        fun newInstance(listener: DateListener): DatePickerFragment {
            dateListener = listener
            return DatePickerFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val minimumYear = Calendar.getInstance()

        context?.let {
            val dialog = DatePickerDialog(it, this, year, month, day)
            dialog.datePicker.minDate = minimumYear.timeInMillis
            return dialog
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val adjustedMonth = month + 1
        val calendar = GregorianCalendar(year, month, dayOfMonth)
        val date = "$adjustedMonth/$dayOfMonth/$year"
        val timeStamp = calendar.timeInMillis
        dateListener?.onDateSet(date,timeStamp)
    }

}