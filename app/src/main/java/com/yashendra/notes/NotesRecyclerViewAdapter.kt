package com.yashendra.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRecyclerViewAdapter(val context: Context,val listener:InotesRVAdapterHandling):RecyclerView.Adapter<NotesRecyclerViewAdapter.NoteviewHolder> (){
    val allnotes= arrayListOf<Note>()
    inner  class NoteviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)

        val deleteButton=itemView.findViewById<ImageView>(R.id.deletebutton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteviewHolder {
       val viewHolder=NoteviewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener {
                listener.onitemclicked(allnotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteviewHolder, position: Int) {
        val currentnote=allnotes[position]
        holder.textView.text=currentnote.text
    }

    override fun getItemCount(): Int {
        return allnotes.size
    }
    fun updatelist(newlist:List<Note>){
        allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()
    }

    interface InotesRVAdapterHandling{
        fun onitemclicked(note: Note)
        {

        }
    }
}
