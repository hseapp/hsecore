/*
 * Copyright (c) 2020 National Research University Higher School of Economics
 * All Rights Reserved.
 */

package com.hse.hsecore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hse.core.common.dip
import com.hse.core.ui.bottomsheets.BottomSheetAdapter
import com.hse.core.ui.bottomsheets.Item
import com.hse.core.ui.widgets.BottomSheet
import kotlinx.android.synthetic.main.dialog_settings.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showBottomSheet(v: View) {
        object : BottomSheet(this) {

            private lateinit var recyclerView: RecyclerView

            init {
                peekHeight = dip(600f)
            }

            override fun getView(): View {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.dialog_settings, null, false)
                recyclerView = view.settingsList
                recyclerView.layoutManager = LinearLayoutManager(context)
                val adapter = BottomSheetAdapter()

                val numberPickerItem = Item.NumberPickerItem(
                    prefix = "tset",
                    suffix = "shozp",
                    initProgress = 5,
                    maxValue = 60,
                    minValue = 5,
                    stepSize = 5
                ) { progress ->

                }
                adapter.addItem(
                    Item.SimpleSwitch(
                        "zalupa",
                        false
                    ) { i, pos, isSelected ->
                        i.selected = isSelected
                        if (isSelected) {
                            adapter.addItem(numberPickerItem)
                            adapter.notifyItemInserted(pos + 1)
                        } else {
                            adapter.removeItem(numberPickerItem)
                        }
                    }
                )

                recyclerView.adapter = adapter
                return view
            }

            override fun getBottomView() = null
        }.show()
    }
}
