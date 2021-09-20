package com.johncoimbra.tmdbapimvvmkotlin.extensions

import android.app.Activity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Activity.addFragment(mFLoaded: Fragment, mSFManager: FragmentManager, mFLayout: FrameLayout) {
    val fragment = mSFManager.beginTransaction()
    fragment.replace(mFLayout.id, mFLoaded)
    fragment.commit()
}