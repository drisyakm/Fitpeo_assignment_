package com.example.fitpeo_assignment.view

interface ItemSelectionListener<T> {
    fun onItemSelected(item:T?,position:Int)
}