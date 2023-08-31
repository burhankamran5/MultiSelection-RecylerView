package com.bkcoding.multiselectionrecylerview

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bkcoding.multiselectionrecyclerview.MultiSelectAdapter
import com.bkcoding.multiselectionrecylerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: MultiSelectAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAdapter = MultiSelectAdapter(dataSet) { show -> showDeleteMenu(show) }
        binding.RV.adapter = mAdapter
        binding.RV.layoutManager = LinearLayoutManager(this)

    }

    data class DataSet(
        val number: Int,
        var isSelected: Boolean
    )

    val dataSet = mutableListOf(
        DataSet(1, false),
        DataSet(2, false),
        DataSet(3, false),
        DataSet(4, false),
        DataSet(5, false),
        DataSet(6, false),
        DataSet(7, false),
        DataSet(8, false),
        DataSet(9, false),
        DataSet(10, false),
        DataSet(11, false),
        DataSet(12, false),
        DataSet(13, false),
        DataSet(14, false),
        DataSet(15, false),
        DataSet(16, false),
        DataSet(17, false),
        DataSet(18, false),
        DataSet(19, false),
        DataSet(20, false),
    )

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mainMenu = menu
        Log.d("MenuDebug", "onCreateOptionsMenu is called")
        menuInflater.inflate(R.menu.custom_menu, mainMenu)
        showDeleteMenu(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                delete()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showDeleteMenu(show: Boolean) {
        mainMenu?.findItem(R.id.delete)?.isVisible = show
    }


    private fun delete() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete")
        alertDialog.setMessage("Do you want to delete this items?")
        alertDialog.setPositiveButton("Delete") { _, _ ->
            mAdapter.deleteSelectedItem()
            showDeleteMenu(true)
        }
        alertDialog.setNegativeButton("Cancel") { _, _ -> }
        alertDialog.show()
    }

}