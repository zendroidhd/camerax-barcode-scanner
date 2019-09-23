package com.technologies.zenlight.defaultapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.technologies.zenlight.defaultapp.R

fun addFragmentHorizontally(fragmentId: Fragment, fragmentManager: FragmentManager, tag: String, args: Bundle?){

    val transaction = fragmentManager.beginTransaction()
    fragmentId.arguments = args

    if (isUnitTesting){//Robolectric cannot handle fragment animations (gets stuck in infinite loop)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()

    } else {
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

fun addFragmentVertically(fragmentId: Fragment, fragmentManager: FragmentManager, tag: String, args: Bundle?){

    val transaction = fragmentManager.beginTransaction()
    fragmentId.arguments = args

    if (isUnitTesting){//Robolectric cannot handle fragment animations (gets stuck in infinite loop)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()

    } else {
        transaction.setCustomAnimations(R.anim.slide_in_up, android.R.anim.fade_out, android.R.anim.fade_in, R.anim.slide_out_down)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

fun addFragmentFadeIn(fragmentId: Fragment, fragmentManager: FragmentManager, tag: String, args: Bundle?) {
    val transaction = fragmentManager.beginTransaction()
    fragmentId.arguments = args

    if (isUnitTesting){//Robolectric cannot handle fragment animations (gets stuck in infinite loop)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()

    } else {
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

fun addFragmentRemoveFromBackstack(fragmentId: Fragment, fragmentManager: FragmentManager, tag: String, args: Bundle?) {
    val transaction = fragmentManager.beginTransaction()
    fragmentId.arguments = args

    if (isUnitTesting) {//Robolectric cannot handle fragment animations (gets stuck in infinite loop)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.commit()
    } else {
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.commit()
    }
}

fun replaceFragmentHorizontally(fragmentId: Fragment, fragmentManager: FragmentManager, tag: String, args: Bundle?){
    val transaction = fragmentManager.beginTransaction()
    fragmentId.arguments = args

    if (isUnitTesting){//Robolectric cannot handle fragment animations (gets stuck in infinite loop)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()

    } else {
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        transaction.replace(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

fun replaceFragmentHorizontallyReversed(fragmentId: Fragment, fragmentManager: FragmentManager, tag: String, args: Bundle?){
    val transaction = fragmentManager.beginTransaction()
    fragmentId.arguments = args

    if (isUnitTesting){//Robolectric cannot handle fragment animations (gets stuck in infinite loop)
        transaction.add(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()

    } else {
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
        transaction.replace(R.id.fragment_container, fragmentId, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


private fun showFragmentWithNoAnimations(){
}